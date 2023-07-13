package com.levdoc.m20service.mapper;

import com.levdoc.m20service.dto.PatientDTO;
import com.levdoc.m20service.dto.UserDTO;
import com.levdoc.m20service.model.PatientModel;
import com.levdoc.m20service.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = GemAnalysisMapper.class)
public interface PatientMapper {

    PatientMapper PATIENT_MAPPER = Mappers.getMapper(PatientMapper.class);

    PatientDTO modelToDto(PatientModel patientModel);
    PatientModel dtoToModel (PatientDTO patientDTO);
    List<PatientDTO> modelsToDtos (List<PatientModel> patientModelList);
    List<PatientModel> dtosToModels (List<PatientDTO> patientDTOList);


}
