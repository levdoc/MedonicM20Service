package com.levdoc.m20service.repository;

import com.levdoc.m20service.dto.GemAnalysisDTO;
import com.levdoc.m20service.model.GemAnalysisModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface GemAnalysisRepository
        extends GenericRepository<GemAnalysisModel> {

    GemAnalysisModel findGemAnalysisModelById(Long id);

    List<GemAnalysisModel> findAllByAppointmentDate(LocalDate localDate);

    @Query(nativeQuery = true,
            value = """
                    select * from analysis where appointment_date=:date;
                    """)
    List<GemAnalysisModel> searchGemAnalysisByDate(@Param(value = "date") LocalDate localDate);
}
