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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;
    
    private String name;
    private String description;
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "team")
    private List<User> users;
    
    public static Team create(String name, String description) {
        return new Team(
                null,
                name,
                description,
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }
}