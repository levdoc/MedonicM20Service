package com.levdoc.m20service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SearchAnalysisDTO {
    private LocalDate appointmentDate;
}
