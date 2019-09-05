package adapters.controller.login;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import constant.HttpStatus;
import constant.ResponseMessage;
import infrastructure.settings.SettingReader;
import infrastructure.settings.yaml.object.Settings;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class OAuth2Google {

	private static final Collection<String> SCOPES = Arrays.asList("email");
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	public static void oath2Redirect(Request req, Response res) throws Exception{
		try{
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

		}catch(Exception e){
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}

	public static void loginProc(Request req, Response res){
		try{
    		//認証確認
    		if(req.session().attribute("state") == null
    				|| !req.queryParams("state").equals((String)req.session().attribute("state"))){
				halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
    		}
    		req.session().removeAttribute("state");
/*
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

			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
			GoogleUserInfo info = gson.fromJson(jsonIdentity, GoogleUserInfo.class);

				return info;
*/
		}catch(Exception e){
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
}
