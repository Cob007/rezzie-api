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

    @Column(nullable = false)
    private String nameOfOrganization;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Date startDate;

    private Boolean active;

    @Column(nullable = false)
    private Date endDate;

    @Lob
    @Column(nullable = false, length=512)
    private String roleDetails;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="user_id", nullable=false)
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
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
