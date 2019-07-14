package adapters.controller.data;

import constant.HttpStatus;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class ControllerDataUser {
	public void getDataUser(String reqBody){
		try{
			//データ取得
			//bodyへセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
