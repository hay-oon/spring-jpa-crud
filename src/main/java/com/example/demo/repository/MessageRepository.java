package com.example.demo.repository;

import com.example.demo.repository.entity.Message;
import com.example.demo.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Optional<Message> findById(Integer id);
    
    List<Message> findAll();
    
    Message save(Message entity);
    
    void deleteById(Integer id);
}