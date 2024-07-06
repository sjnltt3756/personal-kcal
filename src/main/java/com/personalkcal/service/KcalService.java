package com.personalkcal.service;


import com.personalkcal.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class KcalService {

    // 체형별 탄단지 비율 계산
    // 다이어트
    public static double dietKcal(Member member){
       return basicKcal(member)-500;
    }
    // 유지
    public static double maintainKcal(Member member){
        return basicKcal(member);
    }
    // 린매스업
    public static double massUpKcal(Member member){
        return basicKcal(member)+200;
    }
    // 벌크업
    public static double bulkUpKcal(Member member){
        return basicKcal(member)+500;
    }


    // 체형별 칼로리 계산(마름,보통,비만,근육)



    // 기본 권장 칼로리 계산
    private static Double basicKcal(Member member){
        double womanKcal = 447.593 + (9.247 * member.getWeight()) + (3.098 * member.getHeight()) - (4.330 * member.getAge());
        double manKcal = 88.362 + (13.397 * member.getWeight()) + (4.799 * member.getHeight()) - (6.677 * member.getAge());

        if (member.getGender().equals("여성")) {
            return womanKcal;
        }else{
            return manKcal;
        }

    }
}
