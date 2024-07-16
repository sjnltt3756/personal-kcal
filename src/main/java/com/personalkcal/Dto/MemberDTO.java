package com.personalkcal.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberDTO {


    private String nickname;
    private String gender;
    private int height;
    private int weight;
    private int age;

    private KcalDto kcalDto;
}
