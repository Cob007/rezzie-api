package com.rezzie.api.user.volunteerHistory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class VolunteerHistory {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String nameOfOrganization;

    @NotNull
    private String role;

    @NotNull
    private Date startDate;

    private Boolean isActive;

    @NotNull
    private Date endDate;

    @NotNull
    private String roleDetails;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(String roleDetails) {
        this.roleDetails = roleDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}