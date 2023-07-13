package com.levdoc.m20service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
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

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_blocked", columnDefinition = "boolean default false")
    private boolean isBlocked;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "e_mail", nullable = false)
    private String email;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PatientModel> patientModel;
}
