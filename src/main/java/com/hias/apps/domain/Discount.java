package com.hias.apps.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "discount")
@ToString(exclude = {"orderList", "cartList"})
public class Discount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "code")
    @NotBlank
    private String code;

    @Column(name = "discount_price")
    @NotNull
    private Long discountPrice;


    @Column(name = "status", nullable = true)
    @JsonIgnore
    private Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
