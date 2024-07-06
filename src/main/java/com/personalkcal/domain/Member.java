package com.personalkcal.domain;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Member {

    private int height;
    private int weight;
    private int age;
    private String gender;

    public Member(int height, int weight, int age, String gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }



}
