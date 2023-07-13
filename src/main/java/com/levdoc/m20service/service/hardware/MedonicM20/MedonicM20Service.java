package com.levdoc.m20service.service.hardware.MedonicM20;

import com.levdoc.m20service.constants.medonicM20.SampleConstantsName;
import com.levdoc.m20service.model.GemAnalysisModel;
import com.levdoc.m20service.repository.GemAnalysisRepository;
import com.levdoc.m20service.repository.PatientRepository;
import com.levdoc.m20service.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.webjars.NotFoundException;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.levdoc.m20service.constants.medonicM20.xPathExpression.xPathExpToID;
import static com.levdoc.m20service.constants.medonicM20.xPathExpression.xPathExpToResults;

@Service
@Slf4j
public class MedonicM20Service {
    protected final GemAnalysisRepository gemAnalysisRepository;
    protected final PatientRepository patientRepository;

    public MedonicM20Service(GemAnalysisRepository gemAnalysisRepository,
                             PatientRepository patientRepository) {
        this.gemAnalysisRepository = gemAnalysisRepository;
        this.patientRepository = patientRepository;
    }

    public void readSampleResultFromFile(MultipartFile file) {
        Map<String,String> sampleMapResult = new HashMap<>();

        File tmpFile = new File(FileHelper.createFile(file));
        try {
            file.transferTo(tmpFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GemAnalysisModel gemAnalysisModel = gemAnalysisRepository.findById(Long.valueOf(readValesFromFile(xPathExpToID, tmpFile))).orElseThrow(
                () -> new NotFoundException("Назначения не найдено")
        );

        for (String value : SampleConstantsName.MEDONIC_M20_SIMPLE_CONSTANTS_LIST) {
            sampleMapResult.put(value, readValesFromFile(xPathExpToResults.replace("EDIT", value), tmpFile));
            System.out.println(sampleMapResult.toString()); //TODO нужен маппер для заполнения полученного из БД gemAnalysisModel
        }

        tmpFile.delete();

        gemAnalysisRepository.save(ParserMapToModel.createModel(gemAnalysisModel,sampleMapResult));

//        if (ParserMapToModel.createModel(gemAnalysisModel, sampleMapResult) != null) {
//            System.out.println(gemAnalysisModel.toString());
//            gemAnalysisRepository.save(ParserMapToModel.createModel(gemAnalysisModel,sampleMapResult));
//        }

    }

    protected String readValesFromFile(String exp, File filename) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder;
        Document document = null;

        try {
            documentBuilder = builderFactory.newDocumentBuilder();
            document = documentBuilder.parse(filename);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        String result = null;
        try {
            XPathExpression xPathExpression = xPath.compile(exp);
            return (String) xPathExpression.evaluate(document, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

}
