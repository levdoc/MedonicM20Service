package com.levdoc.m20service.mapper;

import com.levdoc.m20service.dto.GemAnalysisDTO;
import com.levdoc.m20service.model.GemAnalysisModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GemAnalysisMapper {

    GemAnalysisMapper GEM_ANALYSIS_MAPPER = Mappers.getMapper(GemAnalysisMapper.class);

    GemAnalysisDTO modelToDto(GemAnalysisModel gemAnalysisModel);
    GemAnalysisModel dtoToModel (GemAnalysisDTO gemAnalysisDTO);
    List<GemAnalysisDTO> modelsToDtos (List<GemAnalysisModel> gemAnalysisModelList);
    List<GemAnalysisModel> dtosToModels (List<GemAnalysisDTO> gemAnalysisDTOList);

}
