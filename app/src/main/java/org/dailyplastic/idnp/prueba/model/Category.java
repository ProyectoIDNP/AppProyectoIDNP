package org.dailyplastic.idnp.prueba.model;

import java.time.LocalDateTime;

public class Category {
    private Integer id;
    private String name;
    private String created;
    private String updated;

    public Category() {

    }

    public Category(Integer id, String name, String created, String updated) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return name;
    }


}
