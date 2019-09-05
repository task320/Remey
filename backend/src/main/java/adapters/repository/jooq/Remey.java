/*
 * This file is generated by jOOQ.
 */
package adapters.repository.jooq;


import adapters.repository.jooq.tables.Balance;
import adapters.repository.jooq.tables.Tags;
import adapters.repository.jooq.tables.TagsMap;
import adapters.repository.jooq.tables.UserSettings;
import adapters.repository.jooq.tables.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Remey extends SchemaImpl {

    private static final long serialVersionUID = 1629268502;

    /**
     * The reference instance of <code>remey</code>
     */
    public static final Remey REMEY = new Remey();

    /**
     * The table <code>remey.balance</code>.
     */
    public final Balance BALANCE = adapters.repository.jooq.tables.Balance.BALANCE;

    /**
     * The table <code>remey.tags</code>.
     */
    public final Tags TAGS = adapters.repository.jooq.tables.Tags.TAGS;

    /**
     * The table <code>remey.tags_map</code>.
     */
    public final TagsMap TAGS_MAP = adapters.repository.jooq.tables.TagsMap.TAGS_MAP;

    /**
     * The table <code>remey.users</code>.
     */
    public final Users USERS = adapters.repository.jooq.tables.Users.USERS;

    /**
     * The table <code>remey.user_settings</code>.
     */
    public final UserSettings USER_SETTINGS = adapters.repository.jooq.tables.UserSettings.USER_SETTINGS;

    /**
     * No further instances allowed
     */
    private Remey() {
        super("remey", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.BALANCE_ID_SEQ,
            Sequences.TAGS_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Balance.BALANCE,
            Tags.TAGS,
            TagsMap.TAGS_MAP,
            Users.USERS,
            UserSettings.USER_SETTINGS);
    }
}
