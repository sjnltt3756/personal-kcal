package com.personalkcal.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;



@Getter
@Entity
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String gender;

    private int height;
    private int weight;
    private int age;

    // private 생성자

    private Member(int height, int weight, int age, String gender, String nickname) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
    }

    /**
     * 정적 팩토리 메서드
     */
    public static Member specOfWoman(int height, int weight, int age, String nickname) {
        return Member.builder()
                .height(height)
                .weight(weight)
                .age(age)
                .gender("여성")
                .nickname(nickname)
                .build();
    }

    public static Member specOfMan(int height, int weight, int age, String nickname) {
        return Member.builder()
                .height(height)
                .weight(weight)
                .age(age)
                .gender("남성")
                .nickname(nickname)
                .build();
    }


}
