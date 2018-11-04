package main;
import static spark.Spark.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

import e.StatusResponse;
import logic.CreateObjectValues;
import logic.ProcessShoppingData;
import settings.SettingReader;
import settings.yaml.object.Settings;
import structure.StandardResponse;

public class Entry {

	static ProcessShoppingData psd = new ProcessShoppingData();
	static CreateObjectValues cov = new CreateObjectValues();

	  private static final Collection<String> SCOPES = Arrays.asList("email", "profile");
	  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static void main(String[] args) {

    	port(8081);

    	options("/*",
    	        (request, response) -> {

    	            String accessControlRequestHeaders = request
    	                    .headers("Access-Control-Request-Headers");
    	            if (accessControlRequestHeaders != null) {
    	                response.header("Access-Control-Allow-Headers",
    	                        accessControlRequestHeaders);
    	            }

    	            String accessControlRequestMethod = request
    	                    .headers("Access-Control-Request-Method");
    	            if (accessControlRequestMethod != null) {
    	                response.header("Access-Control-Allow-Methods",
    	                        accessControlRequestMethod);
    	            }

    	            return "OK";
    	        });

    	after("/remey/*",(request, response) -> response.type("application/json"));
    	after("/remey/*",(request, response) -> response	.header("Access-Control-Allow-Origin", "*"));
    	after("/remey/get/*",(request, response) -> response	.header("Access-Control-Allow-Methods", "GET"));
    	after("/remey/*",(request, response) -> response	.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"));
    	after("/remey/post/*",(request, response) -> response.header("Access-Control-Allow-Methods", "POST, OPTIONS"));

        //月でデータを取得
    	get("/remey/get/month/:year/:month/:user", (req, res) -> { return psd.getMonthlyShopping(req);});

        //ページロード時に必要なデータを取得
        get("/remey/get/pull-down-year-month-values/:user", (req, res) -> { return cov.getPullDownYearMonthValues(req); });

    	//認証
    	get("/remey/get/auth_google", (req, res) -> {
    		String state = new BigInteger(130, new SecureRandom()).toString(32);
    		req.session().attribute("state", state);

    		Settings settings = SettingReader.getSettings();
    		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
    				 HTTP_TRANSPORT,
    			     JSON_FACTORY,
    			     settings.getAuthSettings().getClientId(),
    			     settings.getAuthSettings().getClientSecret(),
    			     SCOPES).build();

    		String url = flow
    				.newAuthorizationUrl()
    				.setRedirectUri(settings.getAuthSettings().getCallback())
    				.setState(state)
    				.build();

    		res.redirect(url);

    		return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    	});


    	get("/remey/get/auth_google_callback", (req, res)->{
			try{
			//認証確認
			if(req.session().attribute("state") == null
			|| !req.queryParams("state").equals((String)req.session().attribute("state"))){
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
			}

			req.session().removeAttribute("state");

			Settings settings = SettingReader.getSettings();
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					 HTTP_TRANSPORT,
				     JSON_FACTORY,
				     settings.getAuthSettings().getClientId(),
				     settings.getAuthSettings().getClientSecret(),
				     SCOPES).build();

			TokenResponse tokenResponse = flow.newTokenRequest(req.queryParams("code"))
												.setRedirectUri(settings.getAuthSettings().getCallback())
												.execute();

			Credential credential = flow.createAndStoreCredential(tokenResponse, null);
			HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);

			GenericUrl url = new GenericUrl(settings.getAuthSettings().getUserInfoEndPoint());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.getHeaders().setContentType("application/json");

			String jsonIdentity = request.execute().parseAsString();

				return new Gson().toJson(
						new StandardResponse(StatusResponse.SUCCESS));
			}catch(Exception e){
				System.out.print(e.getMessage());
				System.out.print(e.getStackTrace().toString());
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
			}
    	});

        //データ登録
    	post("/remey/post/:user", (req, res) -> { return psd.insert(req); });



    }
}