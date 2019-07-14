package adapters.controller.data;

import constant.HttpStatus;
import adapters.request.data.*;
import static spark.Spark.halt;


public class ControllerDataBalance {

	public void responseDataDay(String reqBodyJson){
		try{
			ReqParamsGetDataDay req = new ReqParamsGetDataDay(reqBodyJson);
			//データ取得
			//bodyへセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void responseDataMonth(String reqBodyJson){
		try{
			ReqParamsGetDataMonth req = new ReqParamsGetDataMonth(reqBodyJson);
			//データ取得
			//bodyへセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public void responseDataYear(String reqBodyJson){
		try{
			ReqParamsGetDataYear req = new ReqParamsGetDataYear(reqBodyJson);
			//データ取得
			//bodyへセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public void responseResultInsertDataDayRecord(String reqBodyJson){
		try{
			ReqParamsInsertDataDay req = new ReqParamsInsertDataDay(reqBodyJson);
			//データ取得
			//ステータスセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void responseResultUpdateDataDayRecord(String reqBodyJson){
		try{
			ReqParamsUpdateDataDay req = new ReqParamsUpdateDataDay(reqBodyJson);
			//データ取得
			//ステータスセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void responseResultDeleteDataDayRecord(String reqBodyJson){
		try{
			ReqParamsDeleteDataDay req = new ReqParamsDeleteDataDay(reqBodyJson);
			//データ取得
			//ステータスセット
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
