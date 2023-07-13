package com.levdoc.m20service.constants.medonicM20;

import java.util.List;

public interface SampleConstantsName {
    List<String> MEDONIC_M20_SIMPLE_CONSTANTS_LIST = List.of(
            "RBC", // Уровень эритроцитов
            "PLT", // Уровень тромбоцитов
            "WBC", // Уровень лейкоцитов
            "HGB", // Уровень гемоглобина
            "HCT", // Гематокрит
            "LR", // Лимфоциты %
            "GR", // Гранулоциты %
            "MR" // Клетки среднего размера %
    );

}
