package com.personalkcal.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Kcal {
    private double dietKcal;
    private double maintainKcal;
    private double massUpKcal;
    private double bulkUpKcal;
}
