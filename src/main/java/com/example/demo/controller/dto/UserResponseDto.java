package com.example.demo.controller.dto;

import com.example.demo.repository.entity.Message;
import com.example.demo.repository.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Integer id;
    private String username;
    private String name;
    private String job;
    private String specialty;

    // 팀 정보 추가
    private Integer teamId;
    private String teamName;

    // 메시지 정보 추가
    private List<MessageSummaryDto> messages;

    // 메시지 정보를 간략하게 표현하는 내부 클래스
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MessageSummaryDto {
        private Integer id;
        private String content;

        public static MessageSummaryDto from(Message message) {
            return new MessageSummaryDto(
                message.getId(),
                message.getContent()
            );
        }
    }

    public static UserResponseDto from(User entity) {
        // 팀 정보 처리 (팀이 없을 수 있음)
        Integer teamId = entity.getTeam() != null ? entity.getTeam().getId() : null;
        String teamName = entity.getTeam() != null ? entity.getTeam().getName() : null;

        // 메시지 목록 변환
        List<MessageSummaryDto> messageSummaries = entity.getMessages() != null ?
                entity.getMessages().stream()
                        .map(MessageSummaryDto::from)
                        .collect(Collectors.toList()) : 
                List.of();

        return new UserResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getName(),
                entity.getJob(),
                entity.getSpecialty(),
                teamId,
                teamName,
                messageSummaries
        );
    }
}