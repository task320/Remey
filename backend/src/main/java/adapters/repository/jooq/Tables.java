/*
 * This file is generated by jOOQ.
 */
package adapters.repository.jooq;


import adapters.repository.jooq.tables.Balance;
import adapters.repository.jooq.tables.Tags;
import adapters.repository.jooq.tables.TagsMap;
import adapters.repository.jooq.tables.UserSettings;
import adapters.repository.jooq.tables.Users;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in remey
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>remey.balance</code>.
     */
    public static final Balance BALANCE = adapters.repository.jooq.tables.Balance.BALANCE;

    /**
     * The table <code>remey.tags</code>.
     */
    public static final Tags TAGS = adapters.repository.jooq.tables.Tags.TAGS;

    /**
     * The table <code>remey.tags_map</code>.
     */
    public static final TagsMap TAGS_MAP = adapters.repository.jooq.tables.TagsMap.TAGS_MAP;

    /**
     * The table <code>remey.users</code>.
     */
    public static final Users USERS = adapters.repository.jooq.tables.Users.USERS;

    /**
     * The table <code>remey.user_settings</code>.
     */
    public static final UserSettings USER_SETTINGS = adapters.repository.jooq.tables.UserSettings.USER_SETTINGS;
}
