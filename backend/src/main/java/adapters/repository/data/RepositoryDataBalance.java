package adapters.repository.data;

import adapters.repository.jooq.*;
import adapters.repository.jooq.tables.records.BalanceRecord;
import adapters.repository.jooq.tables.records.TagsRecord;
import adapters.request.data.*;
import exceptions.sql.SqlExecuteException;
import infrastructure.connection.IDBConnection;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RepositoryDataBalance implements IRepositoryDataBalance {
    IDBConnection connection;
    Field formatDateCreateAt = DSL.field("date_format({0}, {1})", SQLDataType.DATE, Tables.BALANCE.CREATE_AT, DSL.inline("%Y/%m/%d")).as(Tables.BALANCE.CREATE_AT.getName());
    Field DateCreateAt = DSL.field(Tables.BALANCE.CREATE_AT.getName(), SQLDataType.DATE);
    private final int INITIAL_VERSION = 0;

    public RepositoryDataBalance(IDBConnection connection){
        this.connection = connection;
    }

    public List<entities.Balance> getDataDayRecords(ReqParamsGetDataDay params){
        Result<Record> result = connection.create
                .select()
                .from(Tables.BALANCE)
                .where(Tables.BALANCE.CREATE_AT.ge(params.getDataDateAtTimestamp()))
                .and(Tables.BALANCE.CREATE_AT.lt(params.getDataDatePlusADayAtTimestamp()))
                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                .fetch();

        List<entities.Balance> dayRecords = new LinkedList<>();

        result.forEach(rec -> {
            int id = rec.getValue(Tables.BALANCE.ID.getName(),Integer.TYPE);
            long income = rec.getValue(Tables.BALANCE.INCOME.getName(),long.class);
            long spending = rec.getValue(Tables.BALANCE.SPENDING.getName(),long.class);
            int version = rec.getValue(Tables.BALANCE.VERSION.getName(),Integer.TYPE);
            List<String> tags = getBalanceTagsById(id);

            dayRecords.add(new entities.Balance(id, income, spending, version));

        });

        return dayRecords;

    }

    public entities.SummaryDayBalance getDataDaySummaryRecords(ReqParamsGetDataDay params){
        Record2<long, long> result =
                connection.create
                .select(DSL.sum(Tables.BALANCE.INCOME).as(Tables.BALANCE.INCOME.getName()), DSL.sum(Tables.BALANCE.SPENDING).as(Tables.BALANCE.SPENDING.getName()))
                .from(Tables.BALANCE)
                .where(Tables.BALANCE.CREATE_AT.ge(params.getDataDateAtTimestamp()))
                .and(Tables.BALANCE.CREATE_AT.lt(params.getDataDatePlusADayAtTimestamp()))
                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                .groupBy(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING)
                .fetchOne();

        return new entities.SummaryDayBalance(
                params.getDataDate(),
                result.getValue(Tables.BALANCE.INCOME.getName(), long.class),
                result.getValue(Tables.BALANCE.SPENDING.getName(), long.class)
        );
    }
    public List<entities.SummaryDayBalance> getDataMonthRecords(ReqParamsGetDataMonth params){
        Result<Record3> result = connection.create
                .select(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING, DateCreateAt)
                .from(
                        connection.create
                                .select(
                                        Tables.BALANCE.INCOME,
                                        Tables.BALANCE.SPENDING,
                                        formatDateCreateAt
                                )
                                .from(Tables.BALANCE)
                                .where(Tables.BALANCE.CREATE_AT.ge(params.getDataDateAtTimestamp()))
                                .and(Tables.BALANCE.CREATE_AT.lt(params.getDataDatePlusAMonthAtTimestamp()))
                                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                )
                .groupBy(DateCreateAt)
                .orderBy(DateCreateAt.asc())
                .fetch();

        List<entities.SummaryDayBalance> monthRecords = new LinkedList<>();

        result.forEach(rec -> {
            Instant InstantCreateAt = rec.getValue(Tables.BALANCE.CREATE_AT.getName(), Date.class).toInstant();
            LocalDate date = LocalDate.ofInstant(InstantCreateAt, ZoneId.systemDefault());
            long income = rec.getValue(Tables.BALANCE.INCOME.getName(),long.class);
            long spending = rec.getValue(Tables.BALANCE.SPENDING.getName(),long.class);

            monthRecords.add(new entities.SummaryDayBalance(date, income, spending));

        });

        return monthRecords;

    }
    public entities.SummaryMonthBalance getDataMonthSummaryRecords(ReqParamsGetDataMonth params){
        Record2<BigDecimal, BigDecimal> result =
                connection.create
                        .select(DSL.sum(Tables.BALANCE.INCOME).as(Tables.BALANCE.INCOME.getName()), DSL.sum(Tables.BALANCE.SPENDING).as(Tables.BALANCE.SPENDING.getName()))
                        .from(Tables.BALANCE)
                        .where(Tables.BALANCE.CREATE_AT.ge(params.getDataDateAtTimestamp()))
                        .and(Tables.BALANCE.CREATE_AT.lt(params.getDataDatePlusAMonthAtTimestamp()))
                        .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                        .groupBy(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING)
                        .fetchOne();

        return new entities.SummaryMonthBalance(
                params.getDataDate(),
                result.getValue(Tables.BALANCE.INCOME.getName(), long.class),
                result.getValue(Tables.BALANCE.SPENDING.getName(), long.class)
        );
    }
    public List<entities.SummaryMonthBalance> getDataYearRecords(ReqParamsGetDataYear params){
        Result<Record3<long, long, Date>> result = connection.create
                .select(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING, DateCreateAt)
                .from(
                        connection.create
                                .select(
                                        Tables.BALANCE.INCOME,
                                        Tables.BALANCE.SPENDING,
                                        formatDateCreateAt
                                )
                                .from(Tables.BALANCE)
                                .where(Tables.BALANCE.CREATE_AT.ge(params.getDataDateAtTimestamp()))
                                .and(Tables.BALANCE.CREATE_AT.lt(params.getDataDatePlusAYearAtTimestamp()))
                                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                )
                .groupBy(DateCreateAt)
                .orderBy(DateCreateAt.asc())
                .fetch();

        List<entities.SummaryMonthBalance> yearRecords = new LinkedList<>();

        result.forEach(rec -> {
            Instant InstantCreateAt = rec.getValue(Tables.BALANCE.CREATE_AT.getName(), Date.class).toInstant();
            LocalDate date = LocalDate.ofInstant(InstantCreateAt, ZoneId.systemDefault());
            long income = rec.getValue(Tables.BALANCE.INCOME.getName(),long.class);
            long spending = rec.getValue(Tables.BALANCE.SPENDING.getName(),long.class);

            yearRecords.add(new entities.SummaryMonthBalance(date, income, spending));
        });

        return yearRecords;
    }
    public entities.SummaryYearBalance getDataYearSummaryRecords(ReqParamsGetDataYear params){
        Record2<BigDecimal, BigDecimal> result =
                connection.create
                        .select(DSL.sum(Tables.BALANCE.INCOME).as(Tables.BALANCE.INCOME.getName()), DSL.sum(Tables.BALANCE.SPENDING).as(Tables.BALANCE.SPENDING.getName()))
                        .from(Tables.BALANCE)
                        .where(Tables.BALANCE.CREATE_AT.ge(params.getDataDateAtTimestamp()))
                        .and(Tables.BALANCE.CREATE_AT.lt(params.getDataDatePlusAYearAtTimestamp()))
                        .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                        .groupBy(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING)
                        .fetchOne();

        return new entities.SummaryYearBalance(
                params.getDataDate(),
                result.getValue(Tables.BALANCE.INCOME.getName(), long.class),
                result.getValue(Tables.BALANCE.SPENDING.getName(), long.class)
        );
    }
    public Result<Record> insertDataDay(ReqParamsInsertDataDay params){
        connection.create.transaction(transaction ->{
            try {
                DSLContext ctx = DSL.using(transaction);
                Record1<Integer> insertResult =  ctx.insertInto(
                        Tables.BALANCE,
                        Tables.BALANCE.USERS_ID,
                        Tables.BALANCE.INCOME,
                        Tables.BALANCE.SPENDING,
                        Tables.BALANCE.VERSION,
                        Tables.BALANCE.CREATE_AT,
                        Tables.BALANCE.UPDATE_AT).
                        values(
                                params.getUsersId(),
                                params.getIncome(),
                                params.getSpending(),
                                INITIAL_VERSION,
                                Timestamp.valueOf(LocalDateTime.now()),
                                Timestamp.valueOf(LocalDateTime.now())).
                        returningResult(Tables.BALANCE.ID).
                        fetchOne();

                if(insertResult.getValue(Tables.BALANCE.ID) == null) {
                    throw new Exception("");
                }

                insertTags(ctx, params.getUsersId(), )
            }catch(Exception e){
                throw new SqlExecuteException(e);
            }
        });

        return;

    }
    public Result<Record> updateDataDay(ReqParamsUpdateDataDay params){
        insertTags(params.getUsersId(), params.getBalanceId(), params.getTags());
        connection.create.
                update(Tables.BALANCE).
                set(Tables.BALANCE.INCOME, params.getIncome()).
                set(Tables.BALANCE.SPENDING, params.getSpending()).
                set(Tables.BALANCE.VERSION, Tables.BALANCE.VERSION.add(1)).
                set(Tables.BALANCE.UPDATE_AT, Timestamp.valueOf(LocalDateTime.now())).
                where(Tables.BALANCE.USERS_ID.eq(params.getUsersId())).
                and(Tables.BALANCE.ID.eq(params.getBalanceId())).
                execute();
    }
    public Result<Record> deleteDataDay(ReqParamsDeleteDataDay params){

    }

    private List<String> getBalanceTagsById(int id){
        Result<Record> result = connection.create
                .select()
                .from(Tables.TAGS_MAP)
                .leftJoin(Tables.TAGS).on(Tables.TAGS_MAP.TAGS_ID.eq(Tables.TAGS.ID))
                .where(Tables.TAGS_MAP.BALANCE_ID.eq(id))
                .fetch();

        List<String> tags = new ArrayList<>();

        result.forEach(rec -> {
            String tag = rec.getValue(Tables.TAGS.NAME, String.class);
            tags.add(tag);
        });

        return tags;
    }

    private void insertTags(int userId, int balanceId, List<String> tags){

        connection.create.deleteFrom(Tables.TAGS_MAP).where(Tables.TAGS_MAP.BALANCE_ID.eq(balanceId));

        tags.forEach(tag -> {
            Record1<Integer> result =  connection.create
                    .select(Tables.TAGS.ID)
                    .from(Tables.TAGS)
                    .where(Tables.TAGS.USERS_ID.eq(userId))
                    .and(Tables.TAGS.NAME.eq(tag))
                    .fetchOne();

            int tagId;
            if(result == null){
                TagsRecord tagsRecord =
                        connection.create.
                                insertInto(
                                        Tables.TAGS,
                                        Tables.TAGS.NAME,
                                        Tables.TAGS.USERS_ID).
                                values(
                                        tag,
                                        userId).
                                returning(
                                        Tables.TAGS.ID).
                                fetchOne();

                tagId  = tagsRecord.getId();
            }else {
                tagId = result.get(Tables.TAGS.ID);
            }

            connection.create.
                    insertInto(
                            Tables.TAGS_MAP,
                            Tables.TAGS_MAP.BALANCE_ID,
                            Tables.TAGS_MAP.TAGS_ID).
                    values(
                            balanceId,
                            tagId
                    ).execute();
        });

    }
}
