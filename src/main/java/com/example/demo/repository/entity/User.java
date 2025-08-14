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

    //Team 과는 단방향 설정 // 왜 Team은 단방향
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

//  - UserService 클래스 내 @PostConstruct `init` 메서드 내 유저 각각에 직접 팀을 주입해주자
//- User 에 Team 을 할당할 수 있는 API 가 존재하지 않기때문에 `init` 메서드에서 직접 주입해줘야한다

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
                new ArrayList<>(),
                null
        );
    }
}
