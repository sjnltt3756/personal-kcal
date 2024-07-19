package com.personalkcal.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MemberDTO {

    private Long id;
    private String nickname;
    private String gender;
    private double height;
    private double weight;
    private int age;

    private KcalDTO kcalDto;
}
