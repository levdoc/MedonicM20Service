package com.levdoc.m20service.dto;

import com.levdoc.m20service.model.PatientModel;
import com.levdoc.m20service.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String password;
    private boolean isBlocked;
    private Role role;

    private String email;
    private List<PatientDTO> patientModel;
}
