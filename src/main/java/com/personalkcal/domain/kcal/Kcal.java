package com.personalkcal.domain.kcal;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Kcal {
    private Double dietKcal;
    private Double maintainKcal;
    private Double massUpKcal;
    private Double bulkUpKcal;

    @Builder
    public Kcal(Double dietKcal,Double maintainKcal, Double massUpKcal,Double bulkUpKcal){
        this.dietKcal=dietKcal;
        this.maintainKcal=maintainKcal;
        this.massUpKcal=massUpKcal;
        this.bulkUpKcal=bulkUpKcal;
    }
}
