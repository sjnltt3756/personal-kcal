package com.personalkcal.service;

import com.personalkcal.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class KcalServiceTest {


    @Test
    public void dietTest(){
        Member m = new Member(177,72,28,"남성");
        KcalService.dietKcal(m);
    }
}