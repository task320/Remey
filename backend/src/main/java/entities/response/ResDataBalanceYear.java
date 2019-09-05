package entities.response;

import entities.SummaryMonthBalance;
import entities.SummaryYearBalance;

import java.util.List;

public class ResDataBalanceYear {
    //年データの取得
    List<SummaryMonthBalance> balances;
    //年データのサマリー
    SummaryYearBalance summary;


    /**
     * コンストラクタ
     * @param balances 年収支データ
     * @param summary 年収支合計
     */
    public ResDataBalanceYear(List<SummaryMonthBalance> balances, SummaryYearBalance summary) {
        this.balances = balances;
        this.summary = summary;
    }
}
