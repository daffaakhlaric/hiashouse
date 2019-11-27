package com.hias.apps.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {

    @JsonProperty("userId")
    private Long userId;

    @JsonCreator
    public AddressDto(

            @JsonProperty("userId") Long userId) {
        this.userId = userId;

    }



    @JsonProperty("firstName")
    private String firstName;


    @JsonProperty("lastName")
    private String lastName;


    @JsonProperty("companyName")
    private String companyName;


    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;


    @JsonProperty("postCode")
    private String postCode;


    @JsonProperty("address")
    private String address;

    @JsonProperty("email")
    private String email;


    @JsonProperty("phone")
    private String phone;


    public Long getUserId(Long userId) {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName(String firstName) {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(String lastName) {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName(String company) {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry(String country) {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity(String city) {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode(String postCode) {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress(String address) {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone(String phone) {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
