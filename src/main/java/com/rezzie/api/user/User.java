package com.rezzie.api.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.contact.ContactInformation;
import com.rezzie.api.user.education.Education;
import com.rezzie.api.user.headline.Headline;
import com.rezzie.api.user.licenseAndCertificate.LicenseAndCertificate;
import com.rezzie.api.user.volunteerHistory.VolunteerHistory;
import com.rezzie.api.user.workExperience.WorkExperience;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private Boolean isActive;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private ContactInformation contactInformation;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Headline headline;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WorkExperience> workExperiences;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Education> educations;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<VolunteerHistory> volunteerHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<LicenseAndCertificate> licenseAndCertificates;

    protected User() { }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<VolunteerHistory> getVolunteerHistories() {
        return volunteerHistories;
    }

    public void setVolunteerHistories(List<VolunteerHistory> volunteerHistories) {
        this.volunteerHistories = volunteerHistories;
    }

    public List<LicenseAndCertificate> getLicenseAndCertificates() {
        return licenseAndCertificates;
    }

    public void setLicenseAndCertificates(List<LicenseAndCertificate> licenseAndCertificates) {
        this.licenseAndCertificates = licenseAndCertificates;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactInformation=" + contactInformation +
                ", headline=" + headline +
                ", posts=" + posts +
                ", workExperiences=" + workExperiences +
                ", educations=" + educations +
                ", volunteerHistories=" + volunteerHistories +
                ", licenseAndCertificates=" + licenseAndCertificates +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
