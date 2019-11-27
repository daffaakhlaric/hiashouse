package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import com.hias.apps.domain.audit.DateAudit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Table(name = "refund")
public class Refund{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;


    @Column(name = "full_name", nullable = true)
    @JsonProperty("fullName")
    private String fullName;

    @Column(name = "phone", nullable = true)
    @JsonProperty("phone")
    private String phone;

    @Column(name = "place", nullable = true)
    @JsonProperty("place")
    private String place;

    @Column(name = "email", nullable = true)
    @JsonProperty("email")
    private String email;

    @Column(name = "no_order", nullable = true)
    @JsonProperty("noOrder")
    private String noOrder;

    @Column(name = "reason", nullable = true)
    @JsonProperty("reason")
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoOrder() {
        return noOrder;
    }

    public void setNoOrder(String noOrder) {
        this.noOrder = noOrder;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
