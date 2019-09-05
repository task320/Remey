package entities.response;

import entities.Balance;
import entities.SummaryDayBalance;
import entities.SummaryMonthBalance;

import java.util.List;

public class ResDataMonth {
    //月データの取得
    List<SummaryDayBalance> balances;
    //月データのサマリー
    SummaryMonthBalance summary;

    /**
     * コンストラクタ
     * @param balances 月収支データ
     * @param summary 月収支合計
     */
    public ResDataMonth(List<SummaryDayBalance> balances, SummaryMonthBalance summary) {
        this.balances = balances;
        this.summary = summary;
    }
}
