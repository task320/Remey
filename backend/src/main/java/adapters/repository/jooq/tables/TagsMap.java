/*
 * This file is generated by jOOQ.
 */
package adapters.repository.jooq.tables;


import adapters.repository.jooq.Indexes;
import adapters.repository.jooq.Keys;
import adapters.repository.jooq.Remey;
import adapters.repository.jooq.tables.records.TagsMapRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class TagsMap extends TableImpl<TagsMapRecord> {

    private static final long serialVersionUID = 1291828203;

    /**
     * The reference instance of <code>remey.tags_map</code>
     */
    public static final TagsMap TAGS_MAP = new TagsMap();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TagsMapRecord> getRecordType() {
        return TagsMapRecord.class;
    }

    /**
     * The column <code>remey.tags_map.balance_id</code>.
     */
    public final TableField<TagsMapRecord, Integer> BALANCE_ID = createField("balance_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>remey.tags_map.tags_id</code>.
     */
    public final TableField<TagsMapRecord, Integer> TAGS_ID = createField("tags_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>remey.tags_map</code> table reference
     */
    public TagsMap() {
        this(DSL.name("tags_map"), null);
    }

    /**
     * Create an aliased <code>remey.tags_map</code> table reference
     */
    public TagsMap(String alias) {
        this(DSL.name(alias), TAGS_MAP);
    }

    /**
     * Create an aliased <code>remey.tags_map</code> table reference
     */
    public TagsMap(Name alias) {
        this(alias, TAGS_MAP);
    }

    private TagsMap(Name alias, Table<TagsMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private TagsMap(Name alias, Table<TagsMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TagsMap(Table<O> child, ForeignKey<O, TagsMapRecord> key) {
        super(child, key, TAGS_MAP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Remey.REMEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TAGS_MAP_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TagsMapRecord> getPrimaryKey() {
        return Keys.TAGS_MAP_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TagsMapRecord>> getKeys() {
        return Arrays.<UniqueKey<TagsMapRecord>>asList(Keys.TAGS_MAP_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TagsMapRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TagsMapRecord, ?>>asList(Keys.TAGS_MAP__TAGS_MAP_BALANCE_ID_FKEY, Keys.TAGS_MAP__TAGS_MAP_TAGS_ID_FKEY);
    }

    public Balance balance() {
        return new Balance(this, Keys.TAGS_MAP__TAGS_MAP_BALANCE_ID_FKEY);
    }

    public Tags tags() {
        return new Tags(this, Keys.TAGS_MAP__TAGS_MAP_TAGS_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagsMap as(String alias) {
        return new TagsMap(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagsMap as(Name alias) {
        return new TagsMap(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TagsMap rename(String name) {
        return new TagsMap(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TagsMap rename(Name name) {
        return new TagsMap(name, null);
    }
}