package com.personalkcal.Dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class KcalDto {
    private double dietKcal;
    private double maintainKcal;
    private double massUpKcal;
    private double bulkUpKcal;
}
