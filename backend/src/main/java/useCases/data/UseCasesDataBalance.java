package useCases.data;


import adapters.repository.data.IRepositoryDataBalance;
import adapters.request.data.ReqParamsGetDataDay;
import adapters.request.data.ReqParamsGetDataMonth;
import adapters.request.data.ReqParamsGetDataYear;
import adapters.request.data.ReqParamsInsertDataDay;
import entities.Balance;
import entities.SummaryDayBalance;
import entities.SummaryMonthBalance;
import entities.SummaryYearBalance;

import java.util.List;

public class UseCasesDataBalance {
	private IRepositoryDataBalance repository;

	public UseCasesDataBalance(IRepositoryDataBalance repository){
		this.repository = repository;
	}

	//日表示用データ取得
	public String getDataDay(ReqParamsGetDataDay params){
		//日データの取得
		List<Balance> balances = repository.getDataDayRecords(params);
		//日データのサマリー
		SummaryDayBalance summary = repository.getDataDaySummaryRecords(params);
		//返すデータのセットを作成し、返す
		return "";
	}

	//月表示用データ取得
	public String getDataMonth(ReqParamsGetDataMonth params){
		//月データの取得
		List<SummaryDayBalance> balances = repository.getDataMonthRecords(params);
		//月データのサマリー
		SummaryMonthBalance summary  = repository.getDataMonthSummaryRecords(params);

		return "";
	}

	//年表示用データ取得
	public String getDataYear(ReqParamsGetDataYear params){
		//月データの取得
		List<SummaryMonthBalance> balances = repository.getDataYearRecords(params);
		//月データのサマリー
		SummaryYearBalance summary  = repository.getDataYearSummaryRecords(params);
		return "";
	}

	//データ登録
	public boolean resultAddDataDayRecord(ReqParamsInsertDataDay param) {
		//収入・支出データ登録
		repository.insertDataDay(param);

		return true;
	}

	//データ更新
	public boolean resultUpdateDataDayRecord(String id){
		return true;
	}

	//データ削除
	public boolean resultDeleteDataDayRecordString(String id){
		return true;
	}
}
