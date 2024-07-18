package com.personalkcal.service;

import com.personalkcal.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface KcalService {
    void calculateKcalForMember(Member member);

}
