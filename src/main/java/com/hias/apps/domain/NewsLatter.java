package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import com.hias.apps.domain.audit.DateAudit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Table(name = "news_latter")
public class NewsLatter{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Email
    @Column(name = "email", nullable = true)
    @JsonProperty("email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", email=" + email +  "]";
    }
}
