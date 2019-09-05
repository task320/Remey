package entities.response;

import entities.SummaryMonthBalance;
import entities.SummaryYearBalance;

import java.util.List;

public class ResDataYear {
    //年データの取得
    List<SummaryMonthBalance> balances;
    //年データのサマリー
    SummaryYearBalance summary;


    /**
     * コンストラクタ
     * @param balances 年収支データ
     * @param summary 年収支合計
     */
    public ResDataYear(List<SummaryMonthBalance> balances, SummaryYearBalance summary) {
        this.balances = balances;
        this.summary = summary;
    }
}
