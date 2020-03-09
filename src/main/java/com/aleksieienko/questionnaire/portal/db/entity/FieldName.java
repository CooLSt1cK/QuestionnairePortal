package com.aleksieienko.questionnaire.portal.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "field_names")
public class FieldName implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true,nullable = false)
    private String name;

    public FieldName(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FieldName() {
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

    @Override
    public String toString() {
        return "FieldNameDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
