package Episante.back.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class DiagnosisResultDTO {
    private String sessionId;
    private DiseaseDTO mostLikelyDisease;
    private Map<String, DiseaseDTO> diseaseProbabilities;
}
