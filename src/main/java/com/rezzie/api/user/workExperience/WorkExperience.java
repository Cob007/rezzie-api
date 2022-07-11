package com.rezzie.api.user.workExperience;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rezzie.api.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class WorkExperience {

    @Getter @Setter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter @Setter
    @Column(nullable = false)
    private String title;

    @Getter @Setter
    @Column(nullable = false)
    private String employmentType;

    @Getter @Setter
    @Column(nullable = false)
    private String companyName;

    @Getter @Setter
    @Column(nullable = false)
    private String companyLocation;

    @Getter @Setter
    @Column(nullable = false)
    private Date startDate;

    @Getter @Setter
    private Boolean active;

    @Getter @Setter
    @Column(nullable = false)
    private Date endDate;

    @Getter @Setter
    @Lob
    @Column(nullable = false, length=512)
    private String achievement;

    @Getter @Setter
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
