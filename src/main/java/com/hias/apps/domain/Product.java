package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hias.apps.domain.audit.DateAudit;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends DateAudit {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "is_banner", nullable = true)
    @JsonProperty("isBannee")
    private Long isBannee;

    @Column(name = "product_code", nullable = false)
    @JsonProperty("productCode")
    private String productCode;

    @Column(name = "description", nullable = true)
    @JsonProperty("description")
    private String description;

    @Column(name = "overview", nullable = true)
    @JsonProperty("overview")
    private String overview;

    @Column(name = "hash_tag", nullable = true)
    @JsonProperty("hash_tag")
    private String hash_tag;

    @Column(name = "courier_1", nullable = true)
    @JsonProperty("courier_1")
    private String courier_1;

    @Column(name = "courier_2", nullable = true)
    @JsonProperty("courier_2")
    private String courier_2;

    @Column(name = "courier_3", nullable = true)
    @JsonProperty("courier_3")
    private String courier_3;

    @Column(name = "courier_4", nullable = true)
    @JsonProperty("courier_4")
    private String courier_4;

    @Column(name = "color", nullable = true)
    @JsonProperty("color")
    private String color;

    @Column(name = "weight", nullable = true)
    @JsonProperty("weight")
    private String weight;

    @Column(name = "dimensions", nullable = true)
    @JsonProperty("dimensions")
    private String dimensions;

    @Column(name = "sell_count")
    @JsonIgnore
    @JsonProperty("sellCount")
    private Long sellCount;

    @Column(name = "thumbnail_url", nullable = true)
    @JsonProperty("thumbnail")
    private String thumNailUrl;

    @Column(name = "picture_1", nullable = true)
    @JsonProperty("picture_1")
    private String picture_1;

    @Column(name = "picture_2", nullable = true)
    @JsonProperty("picture_2")
    private String picture_2;

    @Column(name = "picture_3", nullable = true)
    @JsonProperty("picture_3")
    private String picture_3;

    @Column(name = "discount", nullable = true)
    @JsonProperty("discount")
    private Long discount;

    @Column(name = "picture_4", nullable = true)
    @JsonProperty("picture_4")
    private String picture_4;

    @Column(name = "picture_5", nullable = true)
    @JsonProperty("picture_5")
    private String picture_5;

    @Column(name = "picture_6", nullable = true)
    @JsonProperty("picture_6")
    private String picture_6;

    @JsonProperty("quantity")
    @Column(name = "quantity", nullable = true)
    @Min(value = 0, message = "*Quantity has to be non negative number")
    private Integer quantity;


    @JsonProperty("price")
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "category_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MiniSubCategory miniSubCategoryProperties;

//    @ManyToOne
//    @JoinColumn(name = "product_variant1")
//    private Product productVariant1;
//
//    @ManyToOne
//    @JoinColumn(name = "product_variant2")
//    private Product productVariant2;

//    @OneToMany(mappedBy = "product")
//    @JsonIgnore
//    private List<CartItem> cartItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Long getIsBannee() {
        return isBannee;
    }

    public void setIsBannee(Long isBannee) {
        this.isBannee = isBannee;
    }

    //    public List<CartItem> getCartItemList() {
//        return cartItemList;
//    }
//
//    public void setCartItemList(List<CartItem> cartItemList) {
//        this.cartItemList = cartItemList;
//    }


    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getCourier_1() {
        return courier_1;
    }

    public void setCourier_1(String courier_1) {
        this.courier_1 = courier_1;
    }

    public String getCourier_2() {
        return courier_2;
    }

    public void setCourier_2(String courier_2) {
        this.courier_2 = courier_2;
    }

    public String getCourier_3() {
        return courier_3;
    }

    public void setCourier_3(String courier_3) {
        this.courier_3 = courier_3;
    }

    public String getCourier_4() {
        return courier_4;
    }

    public void setCourier_4(String courier_4) {
        this.courier_4 = courier_4;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public Float getPrice() {
        return price;
    }

    public String getThumNailUrl() {
        return thumNailUrl;
    }

    public void setThumNailUrl(String thumNailUrl) {
        this.thumNailUrl = thumNailUrl;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getSellCount() {
        return sellCount;
    }

    public void setSellCount(Long sellCount) {
        this.sellCount = sellCount;
    }

    public MiniSubCategory getMiniSubCategoryProperties() {
        return miniSubCategoryProperties;
    }

    public void setMiniSubCategoryProperties(MiniSubCategory miniSubCategoryProperties) {
        this.miniSubCategoryProperties = miniSubCategoryProperties;
    }

    public String getPicture_1() {
        return picture_1;
    }

    public void setPicture_1(String picture_1) {
        this.picture_1 = picture_1;
    }

    public String getPicture_2() {
        return picture_2;
    }

    public void setPicture_2(String picture_2) {
        this.picture_2 = picture_2;
    }

    public String getPicture_3() {
        return picture_3;
    }

    public void setPicture_3(String picture_3) {
        this.picture_3 = picture_3;
    }

    public String getPicture_4() {
        return picture_4;
    }

    public void setPicture_4(String picture_4) {
        this.picture_4 = picture_4;
    }

    public String getPicture_5() {
        return picture_5;
    }

    public void setPicture_5(String picture_5) {
        this.picture_5 = picture_5;
    }

    public String getPicture_6() {
        return picture_6;
    }

    public void setPicture_6(String picture_6) {
        this.picture_6 = picture_6;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

//    public Product getProductVariant1() {
//        return productVariant1;
//    }
//
//    public void setProductVariant1(Product productVariant1) {
//        this.productVariant1 = productVariant1;
//    }
//
//    public Product getProductVariant2() {
//        return productVariant2;
//    }
//
//    public void setProductVariant2(Product productVariant2) {
//        this.productVariant2 = productVariant2;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
