package com.personalkcal.dto.kcal;

import com.personalkcal.domain.Kcal;
import lombok.Data;


public record KcalDTO(
        double dietKcal,
        double maintainKcal,
        double massUpKcal,
        double bulkUpKcal
) {
    public KcalDTO(Kcal kcal){
        this(
                kcal.getDietKcal(),
                kcal.getMaintainKcal(),
                kcal.getMassUpKcal(),
                kcal.getBulkUpKcal()
        );
    }
}
