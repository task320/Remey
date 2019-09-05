package useCases.data;


import adapters.repository.data.IRepositoryDataBalance;
import entities.request.*;
import entities.Balance;
import entities.SummaryDayBalance;
import entities.SummaryMonthBalance;
import entities.SummaryYearBalance;
import entities.response.ResDataBalnaceDay;
import entities.response.ResDataBalanceMonth;
import entities.response.ResDataBalanceYear;

import java.util.List;

/**
 * 収支ユースケース
 */
public class UseCasesDataBalance {
	private IRepositoryDataBalance repository;

	/**
	 * コンストラクタ
	 * @param repository
	 */
	public UseCasesDataBalance(IRepositoryDataBalance repository){
		this.repository = repository;
	}

	/**
	 * 日表示用データ取得
	 * @param params
	 * @return
	 */
	public ResDataBalnaceDay getDataDay(ReqParamsGetBalanceDay params){
		//日データの取得
		List<Balance> balances = repository.getDataDayRecords(params);
		//日データのサマリー
		SummaryDayBalance summary = repository.getDataDaySummaryRecords(params);

		//表示用データ
		ResDataBalnaceDay response = new ResDataBalnaceDay(balances, summary);

		return response;
	}


	/**
	 * 月表示用データ取得
	 * @param params
	 * @return
	 */
	public ResDataBalanceMonth getDataMonth(ReqParamsGetBalanceMonth params){
		//月データの取得
		List<SummaryDayBalance> balances = repository.getDataMonthRecords(params);
		//月データのサマリー
		SummaryMonthBalance summary  = repository.getDataMonthSummaryRecords(params);

		//表示用データ
		ResDataBalanceMonth response = new ResDataBalanceMonth(balances, summary);

		return response;
	}

	/**sit
	 * 年表示用データ取得
	 * @param params
	 * @return
	 */
	public ResDataBalanceYear getDataYear(ReqParamsGetBalanceYear params){
		//月データの取得
		List<SummaryMonthBalance> balances = repository.getDataYearRecords(params);
		//月データのサマリー
		SummaryYearBalance summary  = repository.getDataYearSummaryRecords(params);

		//表示用データ
		ResDataBalanceYear response = new ResDataBalanceYear(balances, summary);

		return response;
	}

	/**
	 * 収支データ登録
	 * @param params
	 */
	public void addBalance(ReqParamsAddBalance params) {
		repository.addBalance(params);
	}

	/**
	 * 収支データ更新
	 * @param params
	 */
	public void updateBalance(ReqParamsUpdateBalance params){
		repository.updateBalance(params);
	}

	/**
	 * 収支データ削除
	 * @param params
	 */
	public void deleteBalance(ReqParamsDeleteBalance params){
		repository.deleteBalance(params);
	}
}
