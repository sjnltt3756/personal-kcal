package com.personalkcal.dto.chatgpt;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ChatRequestMsgDto {
    private String role;

    private String content;

    @Builder
    public ChatRequestMsgDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
