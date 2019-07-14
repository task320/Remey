/*
 * This file is generated by jOOQ.
 */
package adapters.repository.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class Tags implements Serializable {

    private static final long serialVersionUID = 2127859140;

    private Integer id;
    private Integer usersId;
    private String  name;

    public Tags() {}

    public Tags(Tags value) {
        this.id = value.id;
        this.usersId = value.usersId;
        this.name = value.name;
    }

    public Tags(
        Integer id,
        Integer usersId,
        String  name
    ) {
        this.id = id;
        this.usersId = usersId;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsersId() {
        return this.usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tags (");

        sb.append(id);
        sb.append(", ").append(usersId);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}