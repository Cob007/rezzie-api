package com.rezzie.api.user.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class ContactInformation {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

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
}
