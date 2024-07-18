package com.personalkcal.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberDTO {

    private Long id;
    private String nickname;
    private String gender;
    private double height;
    private double weight;
    private int age;

    private KcalDto kcalDto;
}
