package com.levdoc.m20service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GemAnalysisDTO {
    private Long id;
    private String rbc;
    private String hct;
    private String plt;
    private String wbc;
    private String hgb;
    private String lymPr;
    private String graPr;
    private String midPr;
    private String notes;
    private Boolean isApproved;
    private Boolean isSend;
    private LocalDate appointmentDate;
    private LocalDate dateOfCompletion;
}
