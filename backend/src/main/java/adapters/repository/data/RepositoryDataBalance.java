package adapters.repository.data;

import adapters.repository.jooq.*;
import adapters.repository.jooq.tables.records.BalanceRecord;
import adapters.repository.jooq.tables.records.TagsRecord;
import entities.SummaryDayBalance;
import entities.request.*;
import exceptions.RepositoryException;
import exceptions.SqlExecuteException;
import infrastructure.connection.DBConnection;
import constant.ExceptionsMessage;

import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RepositoryDataBalance implements IRepositoryDataBalance {
    DBConnection connection;
    final static Field formatDateBalanceDate = DSL.field("date_format({0}, {1})", SQLDataType.DATE, Tables.BALANCE.BALANCE_DATE, DSL.inline("%Y/%m/%d")).as(Tables.BALANCE.BALANCE_DATE.getName());
    final static Field DateCreateAt = DSL.field(Tables.BALANCE.CREATE_AT.getName(), SQLDataType.DATE);
    private final int INITIAL_VERSION = 0;

    /**
     *
     * @param connection
     */
    public RepositoryDataBalance(DBConnection connection){
        this.connection = connection;
    }

    /**
     * 日の支出データを取得
     * @param params データ取得に必要なパラメータ
     * @return 日の支出データ
     */
    public List<entities.Balance> getDataDayRecords(ReqParamsGetBalanceDay params){
        Result<BalanceRecord> result = connection.create
                .select()
                .from(Tables.BALANCE)
                .where(Tables.BALANCE.BALANCE_DATE.eq(params.getDataDateAtDate()))
                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                .fetchInto(Tables.BALANCE);

        List<entities.Balance> dayRecords = new LinkedList<>();

        result.forEach(rec -> {
            int id = rec.getId();
            long income = rec.getIncome();
            long spending = rec.getSpending();
            int version = rec.getVersion();
            List<String> tags = getBalanceTagsById(id);

            dayRecords.add(new entities.Balance(id, income, spending, version));

        });

        return dayRecords;
    }

    /**
     * 日の支出合計データを取得
     * @param params　データ取得に必要なパラメータ
     * @return 日の支出合計データ
     */
    public SummaryDayBalance getDataDaySummaryRecords(ReqParamsGetBalanceDay params){
        Record2<BigDecimal, BigDecimal> result =
                connection.create
                .select(DSL.sum(Tables.BALANCE.INCOME).as(Tables.BALANCE.INCOME.getName()), DSL.sum(Tables.BALANCE.SPENDING).as(Tables.BALANCE.SPENDING.getName()))
                .from(Tables.BALANCE)
                .where(Tables.BALANCE.BALANCE_DATE.ge(params.getDataDateAtDate()))
                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                .groupBy(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING)
                .fetchOne();

        return new SummaryDayBalance(
                params.getDataDate(),
                result.getValue(Tables.BALANCE.INCOME.getName(), long.class),
                result.getValue(Tables.BALANCE.SPENDING.getName(), long.class)
        );
    }

    /**
     * 月の収支データ(日単位)を取得
     * @param params データ取得に必要なパラメータ
     * @return 月の収支データ
     */
    public List<entities.SummaryDayBalance> getDataMonthRecords(ReqParamsGetBalanceMonth params){
        Result<Record3<BigDecimal, BigDecimal, Date>> result = connection.create
                .select(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING, DateCreateAt)
                .from(
                        connection.create
                                .select(
                                        Tables.BALANCE.INCOME,
                                        Tables.BALANCE.SPENDING,
                                        formatDateBalanceDate
                                )
                                .from(Tables.BALANCE)
                                .where(Tables.BALANCE.BALANCE_DATE.ge(params.getDataDateAtDate()))
                                .and(Tables.BALANCE.BALANCE_DATE.lt(params.getDataDatePlusAMonthAtDate()))
                                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                )
                .groupBy(DateCreateAt)
                .orderBy(DateCreateAt.asc())
                .fetch();

        List<entities.SummaryDayBalance> monthRecords = new LinkedList<>();

        result.forEach(rec -> {
            Date balanceDate = rec.getValue(Tables.BALANCE.BALANCE_DATE.getName(), Date.class);
            LocalDate date = LocalDate.of(balanceDate.getYear(), balanceDate.getMonth(), balanceDate.getDay());
            long income = rec.getValue(Tables.BALANCE.INCOME.getName(), long.class);
            long spending = rec.getValue(Tables.BALANCE.SPENDING.getName(),long.class);

            monthRecords.add(new entities.SummaryDayBalance(date, income, spending));

        });

        return monthRecords;

    }

    /**
     * 月の収支合計データを取得
     * @param params データ取得に必要なパラメータ
     * @return 月の収支合計データ
     */
    public entities.SummaryMonthBalance getDataMonthSummaryRecords(ReqParamsGetBalanceMonth params){
        Record2<BigDecimal, BigDecimal> result =
                connection.create
                        .select(DSL.sum(Tables.BALANCE.INCOME).as(Tables.BALANCE.INCOME.getName()), DSL.sum(Tables.BALANCE.SPENDING).as(Tables.BALANCE.SPENDING.getName()))
                        .from(Tables.BALANCE)
                        .where(Tables.BALANCE.BALANCE_DATE.ge(params.getDataDateAtDate()))
                        .and(Tables.BALANCE.BALANCE_DATE.lt(params.getDataDatePlusAMonthAtDate()))
                        .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                        .groupBy(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING)
                        .fetchOne();

        return new entities.SummaryMonthBalance(
                params.getDataDate(),
                result.getValue(Tables.BALANCE.INCOME.getName(), long.class),
                result.getValue(Tables.BALANCE.SPENDING.getName(), long.class)
        );
    }

    /**
     * 年の収支データ(月単位)を取得
     * @param params データ取得に必要なパラメータ
     * @return 月の収支合計データ
     */
    public List<entities.SummaryMonthBalance> getDataYearRecords(ReqParamsGetBalanceYear params){
        Result<Record3<Long, Long, Date>> result = connection.create
                .select(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING, DateCreateAt)
                .from(
                        connection.create
                                .select(
                                        Tables.BALANCE.INCOME,
                                        Tables.BALANCE.SPENDING,
                                        formatDateBalanceDate
                                )
                                .from(Tables.BALANCE)
                                .where(Tables.BALANCE.BALANCE_DATE.ge(params.getDataDateAtDate()))
                                .and(Tables.BALANCE.BALANCE_DATE.lt(params.getDataDatePlusAYearAtDate()))
                                .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                )
                .groupBy(DateCreateAt)
                .orderBy(DateCreateAt.asc())
                .fetch();

        List<entities.SummaryMonthBalance> yearRecords = new LinkedList<>();

        result.forEach(rec -> {
            Date balanceDate = rec.getValue(Tables.BALANCE.CREATE_AT.getName(), Date.class);
            LocalDate date = LocalDate.of(balanceDate.getYear(), balanceDate.getMonth(), balanceDate.getDay());
            long income = rec.getValue(Tables.BALANCE.INCOME.getName(),long.class);
            long spending = rec.getValue(Tables.BALANCE.SPENDING.getName(),long.class);

            yearRecords.add(new entities.SummaryMonthBalance(date, income, spending));
        });

        return yearRecords;
    }

    /**
     * 年の収支合計データを取得
     * @param params
     * @return
     */
    public entities.SummaryYearBalance getDataYearSummaryRecords(ReqParamsGetBalanceYear params){
        Record2<BigDecimal, BigDecimal> result =
                connection.create
                        .select(DSL.sum(Tables.BALANCE.INCOME).as(Tables.BALANCE.INCOME.getName()), DSL.sum(Tables.BALANCE.SPENDING).as(Tables.BALANCE.SPENDING.getName()))
                        .from(Tables.BALANCE)
                        .where(Tables.BALANCE.BALANCE_DATE.ge(params.getDataDateAtDate()))
                        .and(Tables.BALANCE.BALANCE_DATE.lt(params.getDataDatePlusAYearAtDate()))
                        .and(Tables.BALANCE.USERS_ID.eq(params.getUsersId()))
                        .groupBy(Tables.BALANCE.INCOME, Tables.BALANCE.SPENDING)
                        .fetchOne();

        return new entities.SummaryYearBalance(
                params.getDataDate(),
                result.getValue(Tables.BALANCE.INCOME.getName(), long.class),
                result.getValue(Tables.BALANCE.SPENDING.getName(), long.class)
        );
    }

    /**
     * 収支データを登録
     * @param params 収支データ
     */
    public void addBalance(ReqParamsAddBalance params){
        connection.create.transaction(transaction ->{
            try {
                DSLContext ctx = DSL.using(transaction);
                BalanceRecord insertResult =  ctx.insertInto(
                        Tables.BALANCE,
                        Tables.BALANCE.USERS_ID,
                        Tables.BALANCE.INCOME,
                        Tables.BALANCE.SPENDING,
                        Tables.BALANCE.VERSION,
                        Tables.BALANCE.BALANCE_DATE,
                        Tables.BALANCE.CREATE_AT,
                        Tables.BALANCE.UPDATE_AT).
                        values(
                                params.getUsersId(),
                                params.getIncome(),
                                params.getSpending(),
                                INITIAL_VERSION,
                                Date.valueOf(params.getDataDate()),
                                Timestamp.valueOf(LocalDateTime.now()),
                                Timestamp.valueOf(LocalDateTime.now())).
                        returning(Tables.BALANCE.ID, Tables.BALANCE.VERSION).
                        fetchOne();

                int balnaceId = insertResult.getId();

                insertTags(ctx, params.getUsersId(), balnaceId, params.getTags());

                //フロント返却用にセット
                params.setBalanceId(balnaceId);


            }catch(Exception e){
                throw new SqlExecuteException(e);
            }
        });

        return;

    }

    /**
     * 収支データの更新
     * @param params 更新データ
     */
    public void updateBalance(ReqParamsUpdateBalance params){
        connection.create.transaction(transaction -> {
            try {
                DSLContext ctx = DSL.using(transaction);

                insertTags(ctx, params.getUsersId(), params.getBalanceId(), params.getTags());

                BalanceRecord record = ctx.
                        update(Tables.BALANCE).
                        set(Tables.BALANCE.INCOME, params.getIncome()).
                        set(Tables.BALANCE.SPENDING, params.getSpending()).
                        set(Tables.BALANCE.VERSION, Tables.BALANCE.VERSION.add(1)).
                        set(Tables.BALANCE.UPDATE_AT, Timestamp.valueOf(LocalDateTime.now())).
                        where(Tables.BALANCE.USERS_ID.eq(params.getUsersId())).
                        and(Tables.BALANCE.ID.eq(params.getBalanceId())).
                        and(Tables.BALANCE.VERSION.le(params.getVersion())).
                        returning(Tables.BALANCE.VERSION).
                        fetchOne();

                if (record.getVersion() != null) {
                    throw new SqlExecuteException(ExceptionsMessage.FAILED_SQL_UPDATE);
                }

                //フロント返却用にセット
                params.setVersion(record.getVersion());

            } catch (Exception e) {
                throw new RepositoryException(e);
            }
        });
    }

    /**
     * 収支データの削除
     * @param params 削除に必要なパラメータ
     */
    public void deleteBalance(ReqParamsDeleteBalance params){
        connection.create.transaction(transaction -> {
            try {
                int deleteCount;
                DSLContext ctx = DSL.using(transaction);

                deleteCount = ctx.
                        deleteFrom(Tables.BALANCE).
                        where(Tables.BALANCE.ID.eq(params.getBalanceId())).
                        execute();

                if (deleteCount != 1) {
                    throw new SqlExecuteException(ExceptionsMessage.FAILED_SQL_DELETE);
                }

                deleteCount = ctx.
                        deleteFrom(Tables.TAGS_MAP).
                        where(Tables.TAGS_MAP.BALANCE_ID.eq(params.getBalanceId())).
                        execute();

                if (deleteCount >= 1) {
                    throw new SqlExecuteException(ExceptionsMessage.FAILED_SQL_DELETE);
                }
            }catch (Exception e){
                throw new RepositoryException(e);
            }

        });
    }

    /**
     *
     * @param TagsMapId タグのマップID
     * @return タグ
     */
    private List<String> getBalanceTagsById(int TagsMapId){
        Result<Record> result = connection.create
                .select()
                .from(Tables.TAGS_MAP)
                .leftJoin(Tables.TAGS).on(Tables.TAGS_MAP.TAGS_ID.eq(Tables.TAGS.ID))
                .where(Tables.TAGS_MAP.BALANCE_ID.eq(TagsMapId))
                .fetch();

        List<String> tags = new ArrayList<>();

        result.forEach(rec -> {
            String tag = rec.getValue(Tables.TAGS.NAME, String.class);
            tags.add(tag);
        });

        return tags;
    }

    /**
     * タグを登録
     * @param ctx　コンテキスト
     * @param userId ユーザID
     * @param balanceId 収支ID
     * @param tags 登録するタグ
     */
    private void insertTags(DSLContext ctx, String userId, int balanceId, List<String> tags){

        ctx.deleteFrom(Tables.TAGS_MAP).where(Tables.TAGS_MAP.BALANCE_ID.eq(balanceId));

        tags.forEach(tag -> {
            Record1<Integer> result =  ctx
                    .select(Tables.TAGS.ID)
                    .from(Tables.TAGS)
                    .where(Tables.TAGS.USERS_ID.eq(userId))
                    .and(Tables.TAGS.NAME.eq(tag))
                    .fetchOne();

            int tagId;
            if(result == null){
                TagsRecord tagsRecord =
                        ctx.
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

            ctx.
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
