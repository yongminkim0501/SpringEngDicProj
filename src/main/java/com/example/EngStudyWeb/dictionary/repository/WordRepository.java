package com.example.EngStudyWeb.dictionary.repository;

import com.example.EngStudyWeb.dictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    // 단어 검색
    Optional<Word> findByWord(String word);

    // 단어 존재 여부 확인
    boolean existsByWord(String word);

    List<Word> findAllByOrderByCreatedAtDesc();
}
