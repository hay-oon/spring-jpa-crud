package com.example.demo.controller;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;
import com.example.demo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> user(@PathVariable Integer id) {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> users(@RequestParam(required = false) String username) {
        List<UserResponseDto> users = Optional.ofNullable(username)
                .map(userService::findByUsername)
                .orElseGet(userService::findAll);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto request) {
        UserResponseDto user = userService.save(request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
//        userService.delete(id);
//        Map<String, Object> response = new HashMap<>();
//        response.put("삭제된 ID", id);
//        response.put("상태", "success");
//        return ResponseEntity.ok(response);
//    }
}
