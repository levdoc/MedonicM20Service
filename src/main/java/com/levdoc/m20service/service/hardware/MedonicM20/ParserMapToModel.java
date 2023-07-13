package com.levdoc.m20service.service.hardware.MedonicM20;

import com.levdoc.m20service.model.GemAnalysisModel;

import java.time.LocalDate;
import java.util.Map;

public class ParserMapToModel {
    private ParserMapToModel() {

    }

    public static GemAnalysisModel createModel(GemAnalysisModel gemAnalysisModel,
                                               Map<String, String> inputMap) {

        for (Map.Entry<String, String> entry : inputMap.entrySet()) {
            switch (entry.getKey()) {
                case "RBC":
                    gemAnalysisModel.setRbc(entry.getValue());
                    break;
                case "PLT":
                    gemAnalysisModel.setPlt(entry.getValue());
                    break;
                case "WBC":
                    gemAnalysisModel.setWbc(entry.getValue());
                    break;
                case "HGB":
                    gemAnalysisModel.setHgb(entry.getValue());
                    break;
                case "HCT":
                    gemAnalysisModel.setHct(entry.getValue());
                    break;
                case "LR":
                    gemAnalysisModel.setLymPr(entry.getValue());
                    break;
                case "GR":
                    gemAnalysisModel.setGraPr(entry.getValue());
                    break;
                case "MR":
                    gemAnalysisModel.setMidPr(entry.getValue());
                    break;
            }

        }

        gemAnalysisModel.setDateOfCompletion(LocalDate.now());

        return gemAnalysisModel;
    }
}
