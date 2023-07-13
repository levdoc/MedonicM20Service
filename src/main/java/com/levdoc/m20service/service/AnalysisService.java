package com.levdoc.m20service.service;

import com.levdoc.m20service.model.GemAnalysisModel;
import com.levdoc.m20service.model.PatientModel;
import com.levdoc.m20service.repository.GemAnalysisRepository;
import com.levdoc.m20service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnalysisService {
    protected final GemAnalysisRepository gemAnalysisRepository;
    protected final PatientRepository patientRepository;

    public AnalysisService(GemAnalysisRepository gemAnalysisRepository,
                           PatientRepository patientRepository) {
        this.gemAnalysisRepository = gemAnalysisRepository;
        this.patientRepository = patientRepository;
    }

    public void createAssignGem(Long id) {
        PatientModel patient = patientRepository.findPatientModelById(id);
        List<GemAnalysisModel> analysisModels = patient.getGemAnalysisModelList();

        GemAnalysisModel analys = new GemAnalysisModel();
        analys.setAppointmentDate(LocalDate.now());
        analys.setIsApproved(false);

        analysisModels.add(analys);

        patientRepository.save(patient);
    }
}
