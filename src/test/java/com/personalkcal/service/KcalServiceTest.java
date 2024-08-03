//package com.personalkcal.service;
//
//import com.personalkcal.domain.kcal.Kcal;
//import com.personalkcal.domain.member.Member;
//import com.personalkcal.service.implementation.Kcal.KcalServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class KcalServiceImplTest {
//
//    private KcalServiceImpl kcalService;
//
//    @BeforeEach
//    void setUp() {
//        kcalService = new KcalServiceImpl();
//    }
//
//    @Test
//    void testCalculateKcalForMember() {
//        Member member = new Member("남성", 70, 175, 25, null);
//        Member result = kcalService.calculateKcalForMember(member);
//
//        assertNotNull(result);
//        assertNotNull(result.getKcal());
//        assertEquals(70, result.getWeight());
//        assertEquals(175, result.getHeight());
//        assertEquals(25, result.getAge());
//        assertEquals("남성", result.getGender());
//
//        Kcal kcal = result.getKcal();
//        assertEquals(1678.0, kcal.getDietKcal());
//        assertEquals(2178.0, kcal.getMaintainKcal());
//        assertEquals(2378.0, kcal.getMassUpKcal());
//        assertEquals(2678.0, kcal.getBulkUpKcal());
//    }
//
//    @Test
//    void testBasicKcalCalculation() {
//        Member maleMember = new Member("남성", 70, 175, 25, null);
//        Member femaleMember = new Member("여성", 60, 165, 30, null);
//
//        double maleKcal = ((KcalServiceImpl) kcalService).basicKcal(maleMember);
//        double femaleKcal = ((KcalServiceImpl) kcalService).basicKcal(femaleMember);
//
//        assertEquals(2178.0, maleKcal);
//        assertEquals(1447.0, femaleKcal);
//    }
//}
