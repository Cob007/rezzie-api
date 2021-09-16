package com.rezzie.api.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.contact.ContactInformation;
import com.rezzie.api.user.education.Education;
import com.rezzie.api.user.headline.Headline;
import com.rezzie.api.user.licenseAndCertificate.LicenseAndCertificate;
import com.rezzie.api.user.skills.Skills;
import com.rezzie.api.user.volunteerHistory.VolunteerHistory;
import com.rezzie.api.user.workExperience.WorkExperience;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private Date dateOfBirth;

    private String gender;

    private Boolean active;

    private ContactInformation contactInformation;


    private Headline headline;


    private List<WorkExperience> workExperiences;

    private List<Education> educations;

    private List<VolunteerHistory> volunteerHistories;

    private List<LicenseAndCertificate> licenseAndCertificates;

    private List<Skills> skills;

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    protected UserResponse() { }


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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", contactInformation=" + contactInformation +
                ", headline=" + headline +
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        active = active;
    }

}
