package com.personalkcal.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Kcal {
    private double dietKcal;
    private double maintainKcal;
    private double massUpKcal;
    private double bulkUpKcal;

    @Builder
    public Kcal(double dietKcal,double maintainKcal, double massUpKcal,double bulkUpKcal){
        this.dietKcal=dietKcal;
        this.maintainKcal=maintainKcal;
        this.massUpKcal=massUpKcal;
        this.bulkUpKcal=bulkUpKcal;
    }
}
