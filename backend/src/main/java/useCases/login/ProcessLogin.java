package useCases.login;

import com.google.gson.Gson;

import dao.DaoUserData;
import constant.StatusResponse;
import spark.Request;
import spark.Response;
import entities.GoogleUserInfo;
import entities.StandardResponse;

public class ProcessLogin {
	public static String loginByGoogleInfo(Request req, Response res){
		try{
    		GoogleUserInfo info = OAuth2Google.getUserInfo(req, res);
			int count = new DaoUserData().selectUserCountByGoogleInfo(info.getSub());
			if(count == 0){
				if(new DaoUserData().insertUserData(info.getSub(), "") <= 0){
					return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
				}
			}
			req.session().attribute("id", info.getSub());
			res.redirect("/infrastructure");
			return  new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
		}catch(Exception e){
			e.printStackTrace();
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
		}

	}


	public static boolean isAuthorize(Request req, Response res) throws Exception{
		String id = req.session().attribute("id");
			int count = new DaoUserData().selectUserCountByGoogleInfo(id);
			if(count >= 1){
				return true;
			}else{
				return false;
			}
	}
}
