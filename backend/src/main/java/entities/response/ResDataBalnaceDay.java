package entities.response;

import entities.Balance;
import entities.SummaryDayBalance;

import java.util.List;

public class ResDataDay {
    //日データの取得
    List<Balance> balances;
    //日データのサマリー
    SummaryDayBalance summary;

    /**
     * コンストラクタ
     * @param balances 日収支データ
     * @param summary 日収支合計
     */
    public ResDataDay(List<Balance> balances, SummaryDayBalance summary) {
        this.balances = balances;
        this.summary = summary;
    }


}
