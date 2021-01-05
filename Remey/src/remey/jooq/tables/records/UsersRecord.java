/*
 * This file is generated by jOOQ.
*/
package remey.jooq.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import remey.jooq.tables.Users;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record4<String, String, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1254396106;

    /**
     * Setter for <code>remey.users.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>remey.users.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>remey.users.password</code>.
     */
    public void setPassword(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>remey.users.password</code>.
     */
    public String getPassword() {
        return (String) get(1);
    }

    /**
     * Setter for <code>remey.users.create_at</code>.
     */
    public void setCreateAt(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>remey.users.create_at</code>.
     */
    public Timestamp getCreateAt() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>remey.users.update_at</code>.
     */
    public void setUpdateAt(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>remey.users.update_at</code>.
     */
    public Timestamp getUpdateAt() {
        return (Timestamp) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, String, Timestamp, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, String, Timestamp, Timestamp> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Users.USERS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Users.USERS.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Users.USERS.CREATE_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return Users.USERS.UPDATE_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getCreateAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component4() {
        return getUpdateAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getCreateAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getUpdateAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value1(String value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value2(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value3(Timestamp value) {
        setCreateAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord value4(Timestamp value) {
        setUpdateAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsersRecord values(String value1, String value2, Timestamp value3, Timestamp value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(String id, String password, Timestamp createAt, Timestamp updateAt) {
        super(Users.USERS);

        set(0, id);
        set(1, password);
        set(2, createAt);
        set(3, updateAt);
    }
}