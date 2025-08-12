package com.example.demo.service;

import com.example.demo.controller.dto.MessageCreateRequestDto;
import com.example.demo.controller.dto.MessageResponseDto;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Message;
import com.example.demo.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    //메세지 발행 API
    public MessageResponseDto createMessage(MessageCreateRequestDto request) {

        Integer userId = request.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다. id=" + userId));

        Message message = Message.create(
                request.getContent(),
                user
        );

        Message created = messageRepository.save(message);
        return MessageResponseDto.from(created);
    }
}
