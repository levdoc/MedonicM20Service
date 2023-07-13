package com.levdoc.m20service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "analysis")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GemAnalysisModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "rbc", nullable = true)
    private String rbc;
    @Column(name = "hct", nullable = true)
    private String hct;
    @Column(name = "plt", nullable = true)
    private String plt;
    @Column(name = "wbc", nullable = true)
    private String wbc;
    @Column(name = "hgb", nullable = true)
    private String hgb;
    @Column(name = "lym_pr", nullable = true)
    private String lymPr;
    @Column(name = "gra_pr", nullable = true)
    private String graPr;
    @Column(name = "mid_pr", nullable = true)
    private String midPr;
    @Column(name = "notes", nullable = true)
    private String notes;
    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private Boolean isApproved;
    @Column(name = "is_send", columnDefinition = "boolean default false")
    private Boolean isSend;
    @Column(name = "appointment_date", nullable = true)
    private LocalDate appointmentDate;
    @Column(name = "date_of_completion", nullable = true)
    private LocalDate dateOfCompletion;



}
