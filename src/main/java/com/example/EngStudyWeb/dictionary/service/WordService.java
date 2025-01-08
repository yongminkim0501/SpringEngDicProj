package com.example.EngStudyWeb.dictionary.service;


import com.example.EngStudyWeb.dictionary.model.Word;
import com.example.EngStudyWeb.dictionary.repository.WordRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordService {
    private final WordRepository wordRepository;

    @Transactional
    public Word saveWord(Word word){
        if (wordRepository.existsByWord(word.getWord())){
            throw new IllegalArgumentException("이미 존재하는 단어 : " + word.getWord());
        }
        return wordRepository.save(word);
    }
    public Word findWord(String word){
        return wordRepository.findByWord(word)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 단어 입니다 : " + word));
    }
    public List<Word> findAllWord(){
        return wordRepository.findAllByOrderByCreatedAtDesc();
    }
    @Transactional // 단어 수정
    public Word updateWord(String word, String newMeaning, String newExample){
        Word existingWord = findWord(word);
        existingWord.setMeaning(newMeaning);
        existingWord.setExample(newExample);
        return wordRepository.save(existingWord);
    }
    @Transactional
    public void deleteWord(String word){
        Word existingWord = findWord(word);
        wordRepository.delete(existingWord);
    }
}
