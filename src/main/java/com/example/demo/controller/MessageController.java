package com.example.demo.controller;

import com.example.demo.controller.dto.MessageCreateRequestDto;
import com.example.demo.controller.dto.MessageResponseDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    //메세지 발행
    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageCreateRequestDto request) {
        MessageResponseDto response = messageService.createMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
