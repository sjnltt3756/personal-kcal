package com.personalkcal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "성별을 입력해주세요.")
    private String gender;
    @Positive
    private Double height;
    @Positive
    private Double weight;
    @Positive
    private Integer age;

    @Embedded
    private Kcal kcal;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Member(Long id, String nickname, String gender, double weight, double height, int age, Kcal kcal) {
        this.id=id;
        this.nickname=nickname;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.kcal = kcal;
    }

    public void updateMember(String nickname, Double height, Double weight, Integer age){
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

}
