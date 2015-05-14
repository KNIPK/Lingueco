package com.lingueco.wordlists.service;

import com.lingueco.wordlists.dao.WordListRepository;
import com.lingueco.wordlists.dao.WordRepository;
import com.lingueco.wordlists.entity.Word;
import com.lingueco.wordlists.entity.WordList;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    @Autowired
    WordRepository wordRepository;
    @Autowired
    GraphDatabase graphDatabase;
    @Autowired
    WordListRepository wordListRepository;

    public void addWord(String listName, String valA, String valB){

        Transaction tx = graphDatabase.beginTx();

        WordList wordList = new WordList();
        Word word = new Word();
        Word translation = new Word();

        try {
            wordList = wordListRepository.getListByName(listName);
            word = new Word(valA, wordList.getLangA());
            translation = new Word(valB, wordList.getLangB());

            wordRepository.save(word);
            wordRepository.save(translation);
            if(!wordRepository.isRelatedTranslation(valA,valB))
                wordRepository.createRelTranslation(valA, valB, wordList.getLangA(), wordList.getLangB());

            wordList.word(word);
            wordList.word(translation);

            wordListRepository.save(wordList);


            tx.success();
        } catch (Exception e) {
            tx.failure();
        } finally {
            tx.close();
        }

    }

    public void wordDelete(String translationValue, String wordValue, String listname){
        Transaction tx = graphDatabase.beginTx();

        try{
            wordRepository.removeWordFromList(translationValue, listname);
            if(wordRepository.getWordRelationsNumber(wordValue) == 0){
                wordRepository.deleteWord(wordValue,listname);
                wordRepository.deleteNodeWithRelations(wordValue,listname);
            }

            if(wordRepository.getWordRelationsNumber(translationValue) == 0)
                wordRepository.deleteNodeWithRelations(translationValue,listname);

            tx.success();
        } catch (Exception e) {
            tx.failure();
            System.out.println(e);
        } finally {
            tx.close();
        }

    }

}
