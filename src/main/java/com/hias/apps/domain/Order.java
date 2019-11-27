package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.dto.ProductItem;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
//@ToString(exclude = "user")
public class Order  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable=false, updatable=false)
    @JsonProperty("userId")
    private User user;

    @Column(name = "user_id")
    @JsonProperty("userId")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_address_id",insertable=false, updatable=false)
    @JsonProperty("userAddressId")
    private UserAddress userAddress;

    @Column(name = "user_address_id")
    @JsonProperty("userAddressId")
    private Long userAddressId;

    @Column(name = "sub_total")
    private Long subTotal;

    @Column(name = "payment_type" , nullable = true)
    private String paymentType;

    @Column(name = "status" , nullable = true)
    private Long status;

    @Column(name = "product_total" , nullable = true)
    private Long productTotal;

    @Column(name = "session" , nullable = true)
    private String session;

    @Column(name = "product_item" , nullable = true,length = 100000)
    private String productItem;

    @Column(name = "total" , nullable = true)
    private Long total;

    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Long productTotal) {
        this.productTotal = productTotal;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getProductItem() {
        return productItem;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setProductItem(List<ProductItem> productItem) {
        this.productItem = String.valueOf(productItem);
    }


}
