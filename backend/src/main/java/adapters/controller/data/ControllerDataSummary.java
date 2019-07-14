package adapters.controller.data;

import constant.HttpStatus;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class ControllerDataSummary {
	public void responseDataTopSummary(Request req, Response rep){
		try{
			//データ取得
			//bodyへセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
