package com.levdoc.m20service.controller;

import com.levdoc.m20service.dto.GemAnalysisDTO;
import com.levdoc.m20service.dto.SearchAnalysisDTO;
import com.levdoc.m20service.dto.UserDTO;
import com.levdoc.m20service.service.GemAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/labworker")
public class LabworkerControllerMVC {

    private final GemAnalysisService gemAnalysisService;

    public LabworkerControllerMVC(GemAnalysisService gemAnalysisService) {
        this.gemAnalysisService = gemAnalysisService;
    }

    @GetMapping
    public String getAllAnalysis(Model model) {
        List<GemAnalysisDTO> analysisDTOS = gemAnalysisService.listAll();
        model.addAttribute("analysis", analysisDTOS);
        return "labworker/viewAllAnalysis";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("analysSearch") SearchAnalysisDTO searchAnalysisDTO,
                         Model model) {
        log.info(searchAnalysisDTO.toString());
        log.info(String.valueOf(searchAnalysisDTO.getAppointmentDate()));
        List<GemAnalysisDTO> analysisDTOS = gemAnalysisService.search(searchAnalysisDTO.getAppointmentDate());
        model.addAttribute("analysis", analysisDTOS);
        return "labworker/viewAllAnalysis";
    }

    @GetMapping("/approved/analys/{id}")
    public String block(@PathVariable Long id) {
        gemAnalysisService.approvedAnalyse(id);
        return "redirect:/labworker";
    }

    @GetMapping("/unapproved/analys/{id}")
    public String unblock(@PathVariable Long id) {
        gemAnalysisService.unApprovedAnalyse(id);
        return "redirect:/labworker";
    }

    @GetMapping("/edit/analys/{id}")
    public String editAnalyseResult(@PathVariable Long id,
                                    Model model) {
        GemAnalysisDTO analysisDTO = gemAnalysisService.findAnalyseById(id);
        model.addAttribute("analyse", analysisDTO);
        return "labworker/editAnalyseResult";
    }

    @PostMapping("/edit/analys/")
    public String editAnalyseResult(@ModelAttribute("analyseUpdate") GemAnalysisDTO analysisDTO) {
        analysisDTO.setDateOfCompletion(LocalDate.now());
        gemAnalysisService.update(analysisDTO);
        return "redirect:/labworker";
    }


}
