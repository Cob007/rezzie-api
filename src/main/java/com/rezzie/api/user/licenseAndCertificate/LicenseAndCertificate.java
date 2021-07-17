package com.rezzie.api.user.licenseAndCertificate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class LicenseAndCertificate {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String nameOfCertificate;

    @NotNull
    private String issuingOrganization;

    @NotNull
    private String role;

    @NotNull
    private Date issueDate;

    private Boolean canExpire;

    @NotNull
    private Date expirationDate;

    @NotNull
    private String credentialId;

    @NotNull
    private String credentialUrl;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNameOfCertificate() {
        return nameOfCertificate;
    }

    public void setNameOfCertificate(String nameOfCertificate) {
        this.nameOfCertificate = nameOfCertificate;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Boolean getCanExpire() {
        return canExpire;
    }

    public void setCanExpire(Boolean canExpire) {
        this.canExpire = canExpire;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }

    public void setCredentialUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
