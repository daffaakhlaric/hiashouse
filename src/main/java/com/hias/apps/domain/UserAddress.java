package com.hias.apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
public class UserAddress {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userAddress;

    @Column(name = "first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "last_name", nullable = true)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "company_name", nullable = true)
    @JsonProperty("companyName")
    private String companyName;

    @Column(name = "country", nullable = false)
    @JsonProperty("country")
    private String country;

    @Column(name = "city", nullable = false)
    @JsonProperty("city")
    private String city;

    @Column(name = "post_code", nullable = false)
    @JsonProperty("postCode")
    private String postCode;

    @Column(name = "address", nullable = false)
    @JsonProperty("address")
    private String address;

    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name = "phone", nullable = false)
    @JsonProperty("phone")
    private String phone;

    public User getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(User userAddress) {
        this.userAddress = userAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
