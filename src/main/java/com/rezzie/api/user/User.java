package com.rezzie.api.user;

import com.rezzie.api.user.contact.ContactInformation;
import com.rezzie.api.user.education.Education;
import com.rezzie.api.user.headline.Headline;
import com.rezzie.api.user.workExperience.WorkExperience;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;

    @OneToOne(mappedBy = "user")
    private ContactInformation contactInformation;

    @OneToOne(mappedBy = "user")
    private Headline headline;

    @OneToMany(mappedBy="user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "user")
    private List<Education> educations;


    protected User() { }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                ", contactInformation=" + contactInformation +
                '}';
    }
}
