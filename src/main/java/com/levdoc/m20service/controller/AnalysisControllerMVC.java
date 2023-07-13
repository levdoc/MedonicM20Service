package com.levdoc.m20service.controller;

import com.levdoc.m20service.service.AnalysisService;
import com.levdoc.m20service.service.hardware.MedonicM20.MedonicM20Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/analysis")
public class AnalysisControllerMVC {

    private final AnalysisService analysisService;
    private final MedonicM20Service medonicM20Service;

    public AnalysisControllerMVC(AnalysisService analysisService,
                                 MedonicM20Service medonicM20Service) {
        this.analysisService = analysisService;
        this.medonicM20Service = medonicM20Service;
    }

    @GetMapping("/assign/geman/{id}")
    public String getPatineInfo(@PathVariable Long id,
                                Model model) {
        analysisService.createAssignGem(id);
        return "redirect:/doctor";
    }

    @GetMapping("/readfromfile")
    public String readSampleResultFromFile() {
        return "labworker/addSimple";
    }


    @PostMapping("/readfromfile")
    public String readSampleResultFromFile(@RequestParam(value = "sampleResult") MultipartFile file) {
        medonicM20Service.readSampleResultFromFile(file);
        return "redirect:/labworker";
    }

}
