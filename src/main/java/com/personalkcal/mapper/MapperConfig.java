package com.personalkcal.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {
    @Bean
    public MemberMapper memberMapper() {
        return Mappers.getMapper(MemberMapper.class);
    }
}
