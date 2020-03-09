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
@Table(name = "fields")
public class Field implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true,nullable = false)
    private String label;
    @Column(name = "type_id",nullable = false,insertable = false,updatable = false)
    private Integer typeId;
    @Column
    private String option;
    @Column(nullable = false)
    private Boolean required;
    @Column(nullable = false)
    private Boolean active;

    @OneToOne(targetEntity = Type.class)
    @JoinColumn(name = "type_id")
    private Type type;

    public Field() {
    }

    public Field(Integer id, String label, Integer typeId, String option, Boolean required, Boolean active, Type type) {
        this.id = id;
        this.label = label;
        this.typeId = typeId;
        this.option = option;
        this.required = required;
        this.active = active;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", typeId='" + typeId + '\'' +
                ", option='" + option + '\'' +
                ", required=" + required +
                ", active=" + active +
                ", type=" + type +
                '}';
    }
    public boolean isValid(){
        return !((type.getName().equals("combobox") || type.getName().equals("radio button"))
                && (option == null || option.isEmpty()));
    }
}
