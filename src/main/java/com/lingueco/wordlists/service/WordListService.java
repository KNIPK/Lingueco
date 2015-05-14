package com.lingueco.wordlists.service;

import com.lingueco.wordlists.dao.WordListRepository;
import com.lingueco.wordlists.dao.WordRepository;
import com.lingueco.wordlists.entity.Word;
import com.lingueco.wordlists.entity.WordList;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WordListService {

    @Autowired
    WordRepository wordRepository;
    @Autowired
    GraphDatabase graphDatabase;
    @Autowired
    WordListRepository wordListRepository;

    public List<WordList> showLists() {
        Transaction tx = graphDatabase.beginTx();

        List<WordList> wordLists = new ArrayList<WordList>();

        try {

            for(WordList wl: wordListRepository.getListsByUser()){
                wordLists.add(wl);
                wl.setWordsAmount(wordListRepository.getWordsAmount(wl.getName(),wl.getLangB()));
                tx.success();
            }

        } catch (Exception e) {
            tx.failure();
            System.out.println(e);
        } finally {
            tx.close();
            return wordLists;
        }

    }

    public void addList(String name, String desc, String langA, String langB){

        Transaction tx = graphDatabase.beginTx();
        WordList wordList = new WordList(name, desc, langA, langB);

        try {

            wordListRepository.save(wordList);

            tx.success();
        } catch (Exception e) {
            tx.failure();
            System.out.println(e);
        } finally {
            tx.close();
        }
    }

    public WordList getListByName(String name) {

        Transaction tx = graphDatabase.beginTx();

        WordList wordList = new WordList();

        try {
            wordList = wordListRepository.getListByName(name);
            tx.success();
        } catch (Exception e) {
            tx.failure();
            System.out.println(e);
        } finally {
            tx.close();
            return wordList;
        }
    }

    public  HashMap<Word, List<Word>> wordsFromList(WordList wordList, String name){

        Transaction tx = graphDatabase.beginTx();

        HashMap<Word, List<Word>> words = new HashMap<Word, List<Word>>();
        try {

            for(Word gw: wordListRepository.getWords(name,wordList.getLangA())) {
                ArrayList<Word> translations = new ArrayList<Word>();
                for(Word translation : wordRepository.getTranslations(gw.value,wordList.getName())) {
                    translations.add(translation);
                }

                if(translations.isEmpty()){
                    wordRepository.removeWordFromList(gw.getValue(), wordList.getName());
                    if(wordRepository.getWordRelationsNumber(gw.getValue()) == 0 )
                        wordRepository.deleteNode(gw.getValue(), wordList.getLangA());

                }
                tx.success();
                words.put(gw, translations);
            }

        } catch (Exception e) {
            tx.failure();
            System.out.println(e);
        } finally {
            tx.close();
            return words;
        }
    }

    public void listEdit(String oldName, String newName, String newDesc){
        Transaction tx = graphDatabase.beginTx();


        try{
            wordListRepository.editWordList(oldName,newName,newDesc);
            tx.success();
        } catch (Exception e) {
            tx.failure();
            System.out.println(e);
        } finally {
            tx.close();
        }

    }

}