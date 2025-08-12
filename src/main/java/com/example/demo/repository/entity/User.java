package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String job;
    private String specialty;
    private LocalDateTime createdAt;

    //Message와 양방향 설정
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // orphanRemoval = true or false?
    private List<Message> messages;

    //Team 과는 단방향 설정 -> 아래 코드 생략
  //@ManyToOne
  //@JoinColumn(name = "team_id")
  //private Team team;


    public static User create(String username, String password, String name, Integer age, String job, String specialty) {
        return new User(
                null,
                username,
                password,
                name,
                age,
                job,
                specialty,
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }
}
