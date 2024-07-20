package com.personalkcal.Dto;


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
