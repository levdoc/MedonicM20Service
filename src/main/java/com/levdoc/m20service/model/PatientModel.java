package com.levdoc.m20service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "date_of_registration", nullable = false)
    private LocalDate dateOfRegistration;

    @Column(name = "e_mail", nullable = false)
    private String email;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isBlocked;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private List<GemAnalysisModel> gemAnalysisModelList;

}
