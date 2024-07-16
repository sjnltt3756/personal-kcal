package com.personalkcal.service;


import com.personalkcal.domain.Kcal;
import com.personalkcal.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class KcalService {


    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void calculateKcalForMember(Member member) {
        Kcal kcal = new Kcal();
        kcal.setDietKcal(dietKcal(member));
        kcal.setMaintainKcal(maintainKcal(member));
        kcal.setMassUpKcal(massUpKcal(member));
        kcal.setBulkUpKcal(bulkUpKcal(member));
        member.setKcal(kcal);
    }



    // 체형별 탄단지 비율 계산
    // 다이어트
    private double dietKcal(Member member){
        if (member.getGender().equals("남성")){

            return basicKcal(member)-500;
        }else{
            return basicKcal(member)-300;
        }
    }
    // 유지
    private double maintainKcal(Member member){

        return basicKcal(member);
    }
    // 린매스업
    private double massUpKcal(Member member){
        if (member.getGender().equals("남성")){

            return basicKcal(member)+200;
        }else{
            return basicKcal(member)+100;
        }

    }
    // 벌크업
    private double bulkUpKcal(Member member){
        if (member.getGender().equals("남성")){

            return basicKcal(member)+500;
        }else{
            return basicKcal(member)+300;
        }
    }


    // 체형별 칼로리 계산(마름,보통,비만,근육)



    // 기본 권장 칼로리 계산
    private Double basicKcal(Member member){
        double womanKcal = 447.593 + (9.247 * member.getWeight()) + (3.098 * member.getHeight()) - (4.330 * member.getAge());
        double manKcal = 88.362 + (13.397 * member.getWeight()) + (4.799 * member.getHeight()) - (6.677 * member.getAge());

        double kcal;
        if ("여성".equals(member.getGender())) {
            kcal = womanKcal;
        } else {
            kcal = manKcal;
        }

        return Double.parseDouble(df.format(kcal));

    }
}
