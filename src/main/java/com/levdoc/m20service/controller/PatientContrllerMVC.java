package com.levdoc.m20service.controller;

import com.google.zxing.WriterException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.levdoc.m20service.dto.GemAnalysisDTO;
import com.levdoc.m20service.dto.PatientDTO;
import com.levdoc.m20service.service.GemAnalysisService;
import com.levdoc.m20service.service.PDFGeneratedService;
import com.levdoc.m20service.service.PatientService;
import com.levdoc.m20service.service.QRCodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/patient")
public class PatientContrllerMVC {

    private final PatientService patientService;
    private final GemAnalysisService gemAnalysisService;
    private final QRCodeService qrCodeService;
    private final PDFGeneratedService pdfGeneratedService;

    public PatientContrllerMVC(PatientService patientService,
                               GemAnalysisService gemAnalysisService,
                               QRCodeService qrCodeService, PDFGeneratedService pdfGeneratedService) {
        this.patientService = patientService;
        this.gemAnalysisService = gemAnalysisService;
        this.qrCodeService = qrCodeService;
        this.pdfGeneratedService = pdfGeneratedService;
    }


    @GetMapping("/{uuid}")
    public String viewPatientResults(@PathVariable UUID uuid,
                                     Model model) {
        PatientDTO patient = patientService.findPatientByUUID(uuid);
        model.addAttribute("patient", patient);
        return "patient/viewPatientInfo";
    }

//    @GetMapping("/{uuid}/analysis/{id}")
//    public String viewPatientResults(@PathVariable UUID uuid,
//                                     @PathVariable Long id,
//                                     Model model){
//        PatientDTO patient = patientService.findPatientByUUID(uuid);
//        GemAnalysisDTO analyse = gemAnalysisService.findAnalyseById(id);
//        model.addAttribute("patient", patient);
//        model.addAttribute("analyse", analyse);
//        return "patient/analysToPdf";
//    }

    @GetMapping("/{uuid}/analysis/{id}")
    public ResponseEntity<?> viewPatientResults(@PathVariable UUID uuid,
                                                @PathVariable Long id) throws IOException, WriterException {

        byte[] bytes = pdfGeneratedService.generateQr(uuid, id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }

}
