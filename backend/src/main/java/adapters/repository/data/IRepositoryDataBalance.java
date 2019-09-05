package adapters.repository.data;

import entities.*;
import entities.request.*;

import java.util.List;

public interface IRepositoryDataBalance {
    List<Balance> getDataDayRecords(ReqParamsGetBalanceDay params);
    SummaryDayBalance getDataDaySummaryRecords(ReqParamsGetBalanceDay params);
    List<SummaryDayBalance> getDataMonthRecords(ReqParamsGetBalanceMonth params);
    SummaryMonthBalance getDataMonthSummaryRecords(ReqParamsGetBalanceMonth params);
    List<SummaryMonthBalance>  getDataYearRecords(ReqParamsGetBalanceYear params);
    SummaryYearBalance getDataYearSummaryRecords(ReqParamsGetBalanceYear params);
    void addBalance(ReqParamsAddBalance params);
    void updateBalance(ReqParamsUpdateBalance params);
    void deleteBalance(ReqParamsDeleteBalance params);
}
