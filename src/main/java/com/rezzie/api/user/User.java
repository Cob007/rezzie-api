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
import lombok.*;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "user",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Getter @Setter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter @Setter
    @Column(nullable = false)
    private String firstName;

    @Getter @Setter
    @Column(nullable = false)
    private String lastName;

    @Getter @Setter
    @Column(nullable = false)
    private String email;

    @Getter @Setter
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Getter @Setter
    @Column(updatable = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    @Getter @Setter
    @Column(updatable = true)
    private String gender;

    @Getter @Setter
    private Boolean active;

    @Getter @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private ContactInformation contactInformation;

    @Getter @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Headline headline;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Post> posts;


    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WorkExperience> workExperiences;


    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Education> educations;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<VolunteerHistory> volunteerHistories;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<LicenseAndCertificate> licenseAndCertificates;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Skills> skills;

/*
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", contactInformation=" + contactInformation +
                ", headline=" + headline +
                ", posts=" + posts +
                ", workExperiences=" + workExperiences +
                ", educations=" + educations +
                ", volunteerHistories=" + volunteerHistories +
                ", licenseAndCertificates=" + licenseAndCertificates +
                '}';
    }*/



}
