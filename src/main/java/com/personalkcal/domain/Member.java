package com.personalkcal.domain;

import com.personalkcal.Dto.RegisterDTO;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String gender;

    private double height;
    private double weight;
    private int age;

    @Embedded
    private Kcal kcal;



}
