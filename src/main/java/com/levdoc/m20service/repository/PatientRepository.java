package com.levdoc.m20service.repository;

import com.levdoc.m20service.model.PatientModel;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository
        extends GenericRepository<PatientModel> {

    PatientModel findPatientModelById(Long id);
    PatientModel findPatientModelByUuid(UUID uuid);

}
