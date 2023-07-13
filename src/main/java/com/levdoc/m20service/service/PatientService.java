package com.levdoc.m20service.service;

import com.levdoc.m20service.dto.PatientDTO;
import com.levdoc.m20service.mapper.PatientMapper;
import com.levdoc.m20service.model.PatientModel;
import com.levdoc.m20service.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    protected final PatientRepository patientRepository;
    protected final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository,
                          PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public PatientDTO findPatientByUUID (UUID uuid) {
        return patientMapper.modelToDto(patientRepository.findPatientModelByUuid(uuid));
    }

    public Page<PatientDTO> findAllPatinetDTO(Pageable pageable) {
        Page<PatientModel> patientModelsPaginated = patientRepository.findAll(pageable);
        List<PatientDTO> result = patientMapper.modelsToDtos(patientModelsPaginated.getContent());
        return new PageImpl<>(result,pageable,patientModelsPaginated.getTotalElements());
    }

}
