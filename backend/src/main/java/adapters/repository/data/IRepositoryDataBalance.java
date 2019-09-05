package adapters.repository.data;

import entities.*;
import adapters.request.data.*;

import java.util.List;

public interface IRepositoryDataBalance {
    List<Balance> getDataDayRecords(ReqParamsGetDataDay params);
    SummaryDayBalance getDataDaySummaryRecords(ReqParamsGetDataDay params);
    List<SummaryDayBalance> getDataMonthRecords(ReqParamsGetDataMonth params);
    SummaryMonthBalance getDataMonthSummaryRecords(ReqParamsGetDataMonth params);
    List<SummaryMonthBalance>  getDataYearRecords(ReqParamsGetDataYear params);
    SummaryYearBalance getDataYearSummaryRecords(ReqParamsGetDataYear params);
    void insertDataDay(ReqParamsInsertDataDay params);
    void updateDataDay(ReqParamsUpdateDataDay params);
    void deleteDataDay(ReqParamsDeleteDataDay params);
}
