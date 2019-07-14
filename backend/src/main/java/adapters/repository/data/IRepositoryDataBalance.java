package adapters.repository.data;

import entities.*;
import org.jooq.Record;
import org.jooq.Result;
import adapters.request.data.*;

import java.util.List;

public interface IRepositoryDataBalance {
    List<Balance> getDataDayRecords(ReqParamsGetDataDay params);
    SummaryDayBalance getDataDaySummaryRecords(ReqParamsGetDataDay params);
    List<SummaryDayBalance> getDataMonthRecords(ReqParamsGetDataMonth params);
    SummaryMonthBalance getDataMonthSummaryRecords(ReqParamsGetDataMonth params);
    List<SummaryMonthBalance>  getDataYearRecords(ReqParamsGetDataYear params);
    SummaryYearBalance getDataYearSummaryRecords(ReqParamsGetDataYear params);
    Result<Record> insertDataDay(ReqParamsInsertDataDay params);
    Result<Record> updateDataDay(ReqParamsUpdateDataDay params);
    Result<Record> deleteDataDay(ReqParamsDeleteDataDay params);
}
