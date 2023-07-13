package com.levdoc.m20service.service;

import com.levdoc.m20service.dto.GemAnalysisDTO;
import com.levdoc.m20service.mapper.GemAnalysisMapper;
import com.levdoc.m20service.model.GemAnalysisModel;
import com.levdoc.m20service.repository.GemAnalysisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class GemAnalysisService {

    protected final GemAnalysisRepository gemAnalysisRepository;
    protected final GemAnalysisMapper gemAnalysisMapper;

    public GemAnalysisService(GemAnalysisRepository gemAnalysisRepository,
                              GemAnalysisMapper gemAnalysisMapper) {
        this.gemAnalysisRepository = gemAnalysisRepository;
        this.gemAnalysisMapper = gemAnalysisMapper;
    }

    public List<GemAnalysisDTO> listAll() {
        return gemAnalysisMapper.modelsToDtos(gemAnalysisRepository.findAll());
    }

    public void approvedAnalyse(Long id) {
        GemAnalysisModel analys = gemAnalysisRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Исследование не найдено")
        );
        analys.setIsApproved(true);
        gemAnalysisRepository.save(analys);
    }

    public void unApprovedAnalyse(Long id) {
        GemAnalysisModel analys = gemAnalysisRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Исследование не найдено")
        );
        analys.setIsApproved(false);
        gemAnalysisRepository.save(analys);
    }

    public GemAnalysisDTO findAnalyseById(Long id) {
        return gemAnalysisMapper.modelToDto(gemAnalysisRepository.findGemAnalysisModelById(id));
    }

    public void update(GemAnalysisDTO analysisDTO) {
        gemAnalysisRepository.save(gemAnalysisMapper.dtoToModel(analysisDTO));
    }

    public List<GemAnalysisDTO> search(LocalDate date) {
        return gemAnalysisMapper.modelsToDtos(gemAnalysisRepository.searchGemAnalysisByDate(date));
    }

}
