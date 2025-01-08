package com.example.EngStudyWeb.dictionary.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "dictionary")
@Getter
@Setter
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;                 // 단어에 접근하기 위한 id
    private String word;             // 단어 이름
    private String meaning;          // 단어 의미
    private String example;          // 단어 예문
    private LocalDateTime createdAt; // 생성 시간

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
