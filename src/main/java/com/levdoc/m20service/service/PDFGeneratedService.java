package com.levdoc.m20service.service;

import com.google.zxing.WriterException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.levdoc.m20service.dto.GemAnalysisDTO;
import com.levdoc.m20service.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PDFGeneratedService {
    protected final PatientService patientService;
    protected final GemAnalysisService gemAnalysisService;
    protected final QRCodeService qrCodeService;

    @Value("${server.port}")
    private String serverPort;

    public PDFGeneratedService(PatientService patientService,
                               GemAnalysisService gemAnalysisService,
                               QRCodeService qrCodeService) {
        this.patientService = patientService;
        this.gemAnalysisService = gemAnalysisService;
        this.qrCodeService = qrCodeService;
    }


    public byte[] generateQr(UUID uuid, Long id) throws IOException, WriterException {
        PatientDTO result = patientService.findPatientByUUID(uuid);
        GemAnalysisDTO analyse = gemAnalysisService.findAnalyseById(id);

        List<GemAnalysisDTO> analys = new ArrayList<>();
        analys.add(analyse);

        result.setGemAnalysisModelList(analys);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        byte[] qrImage = new byte[0];

        qrImage = qrCodeService.getQRCode("http://localhost:"
                + serverPort + "/" + uuid,125,125);
        String qrcode = Base64.getEncoder().encodeToString(qrImage);

        context.setVariable("result", result);
        context.setVariable("qrcode", qrcode);

        String html = templateEngine.process("/templates/pdf-template/analysToPdf", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:" + serverPort);

        HtmlConverter.convertToPdf(html, target, converterProperties);
        return target.toByteArray();
    }
}
