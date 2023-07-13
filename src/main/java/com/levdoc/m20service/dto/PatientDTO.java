package com.levdoc.m20service.dto;

import com.levdoc.m20service.model.GemAnalysisModel;
import com.levdoc.m20service.model.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private UUID uuid;
    private LocalDate dateOfRegistration;
    private String email;
    private boolean isBlocked;
    private List<GemAnalysisDTO> gemAnalysisModelList;

}
