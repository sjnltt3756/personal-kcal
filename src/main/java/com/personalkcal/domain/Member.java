package com.personalkcal.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String nickname;

    private String gender;

    private Double height;
    private Double weight;
    private Integer age;

    @Embedded
    private Kcal kcal;

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

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    public List<Board> boards = new ArrayList<>();


}
