package com.aleksieienko.questionnaire.portal.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire")
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String value;

    @OneToOne(targetEntity = FieldName.class)
    @JoinColumn(name = "name_id", nullable = false)
    private FieldName fieldName;

    public Questionnaire() {
    }

    public Questionnaire(Integer id, String value, FieldName fieldName) {
        this.id = id;
        this.value = value;
        this.fieldName = fieldName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FieldName getFieldName() {
        return fieldName;
    }

    public void setFieldName(FieldName fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", fieldName=" + fieldName +
                '}';
    }
}
