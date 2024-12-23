package com.example.analyzer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QualityDTO {

    private Double highQualityAvgTimeDiff;

    private Double lowQualityAvgTimeDiff;

    private BigDecimal highQualityAvgAcceptRate;

    private BigDecimal lowQualityAvgAcceptRate;

    private BigDecimal highQualityAvgReputation;

    private BigDecimal lowQualityAvgReputation;
}
