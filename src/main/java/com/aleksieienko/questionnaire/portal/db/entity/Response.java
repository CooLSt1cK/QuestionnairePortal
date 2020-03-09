package com.aleksieienko.questionnaire.portal.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "responses")
public class Response implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "questionnaire_response",
            joinColumns = @JoinColumn(name="response_id"),
            inverseJoinColumns = @JoinColumn(name="questionnaire_id"))
    private List<Questionnaire> questionnaires;

    public Response() {
    }

    public Response(Integer id, List<Questionnaire> questionnaires) {
        this.id = id;
        this.questionnaires = questionnaires;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", questionnaires=" + questionnaires +
                '}';
    }
}
