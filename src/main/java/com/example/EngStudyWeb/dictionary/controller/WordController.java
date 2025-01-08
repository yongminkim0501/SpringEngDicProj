package com.example.EngStudyWeb.dictionary.controller;


import com.example.EngStudyWeb.dictionary.model.Word;
import com.example.EngStudyWeb.dictionary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
@RequiredArgsConstructor
public class WordController {
    private final WordService wordService;

    // 단어 저장
    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        return ResponseEntity.ok(wordService.saveWord(word));
    }
    // 특정 단어 조회
    @GetMapping("/{word}")
    public ResponseEntity<Word> getWord(@PathVariable String word) {
        return ResponseEntity.ok(wordService.findWord(word));
    }
    // 모든 단어 조회
    @GetMapping
    public ResponseEntity<List<Word>> getAllWords(){
        return ResponseEntity.ok(wordService.findAllWord());
    }
    // 단어 수정
    @PutMapping("/{word}")
    public ResponseEntity<Word> updateWord(
            @PathVariable String word,
            @RequestParam String meaning,
            @RequestParam String example) {
        return ResponseEntity.ok(wordService.updateWord(word, meaning, example));
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<Void> deleteWord(@PathVariable String word) {
        wordService.deleteWord(word);
        return ResponseEntity.ok().build();
    }
}
