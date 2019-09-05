package adapters.controller.data;

import adapters.repository.data.RepositoryDataBalance;
import constant.HttpStatus;
import constant.ResponseMessage;
import entities.request.*;
import entities.response.ResDataBalnaceDay;
import entities.response.ResDataBalanceMonth;
import entities.response.ResDataBalanceYear;
import infrastructure.connection.DBConnection;
import operator.converter.ConverterJson;
import useCases.data.UseCasesDataBalance;

import static spark.Spark.halt;


public class ControllerDataBalance {

	RepositoryDataBalance repository;

	/**
	 * コンストラクタ
	 * @param connection DBコネクション
	 */
	public ControllerDataBalance(DBConnection connection){
		RepositoryDataBalance repository = new RepositoryDataBalance(connection);
		this.repository = repository;
	}

	/**
	 * 日の収支データ取得
     * @param usersId
	 * @param reqBodyJson
	 */
	public String getBalanceDay(String usersId, String reqBodyJson){
		try{
			ReqParamsGetBalanceDay params = new ReqParamsGetBalanceDay(usersId, reqBodyJson);
			//データ取得
			UseCasesDataBalance useCase = new UseCasesDataBalance(repository);
			ResDataBalnaceDay res = useCase.getDataDay(params);
			//レスポンス内容
			return ConverterJson.objectToJson(res);

		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
			return ResponseMessage.EMPTY;
		}
	}

	/**
	 * 月の収支データ取得
     * @param usersId
     * @param reqBodyJson
	 * @return
	 */
	public String getBalanceMonth(String usersId, String reqBodyJson){
		try{
			//パラメータ取得
			ReqParamsGetBalanceMonth params = new ReqParamsGetBalanceMonth(usersId, reqBodyJson);
			//データ取得
			UseCasesDataBalance useCase = new UseCasesDataBalance(repository);
			ResDataBalanceMonth res = useCase.getDataMonth(params);
			//Jsonへ変換
			return ConverterJson.objectToJson(res);
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
			return ResponseMessage.EMPTY;
		}
	}

	/**
	 * 年の収支データ取得
     * @param usersId
     * @param reqBodyJson
	 * @return
	 */
	public String getBalanceYear(String usersId, String reqBodyJson){
		try{
			ReqParamsGetBalanceYear params = new ReqParamsGetBalanceYear(usersId, reqBodyJson);
			//データ取得
			UseCasesDataBalance useCase = new UseCasesDataBalance(repository);
			ResDataBalanceYear res = useCase.getDataYear(params);
			//Jsonへ変換
			return ConverterJson.objectToJson(res);
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
			return ResponseMessage.EMPTY;
		}
	}

	/**
	 * 収支データ追加
     * @param usersId
     * @param reqBodyJson
	 */
	public String AddBalance(String usersId, String reqBodyJson){
		try{
			ReqParamsAddBalance params = new ReqParamsAddBalance(usersId, reqBodyJson);
			//収支データ登録
			UseCasesDataBalance useCase = new UseCasesDataBalance(repository);
			useCase.addBalance(params);

			return ConverterJson.objectToJson(params);
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
			return ResponseMessage.EMPTY;
		}
	}

	/**
	 * 収支データ更新
     * @param usersId
	 * @param reqBodyJson
	 */
	public String UpdateBalance(String usersId,String reqBodyJson){
		try{
			ReqParamsUpdateBalance params = new ReqParamsUpdateBalance(usersId, reqBodyJson);
			//データ更新
			UseCasesDataBalance useCase = new UseCasesDataBalance(repository);
			useCase.updateBalance(params);
			//パラメータ内容返却
			return ConverterJson.objectToJson(params);
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
			return ResponseMessage.EMPTY;
		}
	}

	/**
	 * 収支データ削除
     * @param usersId
     * @param reqBodyJson
	 */
	public String DeleteBalance(String usersId, String reqBodyJson){
		try{
			ReqParamsDeleteBalance params = new ReqParamsDeleteBalance(usersId, reqBodyJson);
			//データ取得
			UseCasesDataBalance useCase = new UseCasesDataBalance(repository);
			useCase.deleteBalance(params);

			return ResponseMessage.EMPTY;
		}catch(Exception e){
			//ステータス
			halt(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE);
			return ResponseMessage.EMPTY;
		}
	}
}
