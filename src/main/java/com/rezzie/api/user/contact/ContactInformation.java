package com.rezzie.api.user.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class ContactInformation {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String linkedInUrl;

    @Column(nullable = false)
    private String portfolioUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }
}
