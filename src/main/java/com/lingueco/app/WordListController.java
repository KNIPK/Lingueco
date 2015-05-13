package com.lingueco.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.neo4j.graphdb.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lingueco.wordlists.entity.Word;
import com.lingueco.wordlists.entity.WordList;
import com.lingueco.wordlists.dao.WordListRepository;
import com.lingueco.wordlists.dao.WordRepository;

@Controller
@RequestMapping("/wordlists")
public class WordListController {

	private String viewPath = "controller/wordlist/";

	@Autowired
	WordListRepository wordListRepository;
	@Autowired
	WordRepository wordRepository;
	@Autowired
	GraphDatabase graphDatabase;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
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
		}

		model.addAttribute("wordLists", wordLists);
		
		
		return this.viewPath + "main";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addList(Locale locale, Model model) {

		return this.viewPath + "addList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addList(@RequestParam String name,
			@RequestParam String langA, @RequestParam String langB,
			@RequestParam String desc, Locale locale, Model model) {


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
		
		return "redirect:/wordlists/"+name; 
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String listView(@PathVariable String name, Locale locale, Model model) {


		Transaction tx = graphDatabase.beginTx();
		WordList wordList = new WordList();

		HashMap<Word, List<Word>> words = new HashMap<Word, List<Word>>();
		try {
			wordList = wordListRepository.getListByName(name);
			wordList.setWordsAmount(wordListRepository.getWordsAmount(name,wordList.getLangB()));
			tx.success();
			
			
			for(Word gw: wordListRepository.getWords(name,wordList.getLangA())) {
				ArrayList<Word> translations = new ArrayList<Word>();
				for(Word translation : wordRepository.getTranslations(gw.value,wordList.getName())) {
					translations.add(translation);
				}
				
				if(translations.isEmpty()){
					wordRepository.removeWordFromList(gw.getValue(), wordList.getName());
					if(wordRepository.getWordRelationsNumber(gw.getValue()) == 0 ){	// TUTAJ JEST B£¥D
						wordRepository.deleteNode(gw.getValue(), wordList.getLangA());
						System.out.println("USUNIETO SLOWO BEZ TRANSLACJI!");
					}
				}
				
				words.put(gw, translations);
			}	
			
		} catch (Exception e) {
			tx.failure();
			System.out.println(e);
		} finally {
			tx.close();
		}

		model.addAttribute("wordlist", wordList);
		model.addAttribute("words", words);
		return this.viewPath + "listView";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public String addWord(@RequestParam String name,
			@RequestParam String valA, @RequestParam String valB,
			Locale locale, Model model) {


		Transaction tx = graphDatabase.beginTx();
		
		WordList wordList = new WordList();
		Word word = new Word();
		Word translation = new Word();
		
		try {
			wordList = wordListRepository.getListByName(name);
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
			System.out.println(e);
		} finally {
			tx.close();
		}

		return "redirect:/wordlists/"+name;
		}

	@RequestMapping(value = "/{name}/edit", method = RequestMethod.GET)
	public String listEdit(@PathVariable String name, Locale locale, Model model) {


		Transaction tx = graphDatabase.beginTx();
		WordList wordList = new WordList();

		try{
			wordList = wordListRepository.getListByName(name);
			tx.success();	
		} catch (Exception e) {
			tx.failure();
			System.out.println(e);
		} finally {
			tx.close();
		}

		model.addAttribute("wordlist", wordList);
		return this.viewPath + "listEdit";
	}
	
	@RequestMapping(value = "/{name}/edit", method = RequestMethod.POST)
	public String listEdit(@RequestParam String oldName,@RequestParam String newName,
							@RequestParam String newDesc, Locale locale, Model model) {

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


		return "redirect:/wordlists/"+newName; 
	}
	
	@RequestMapping(value = "/{listname}/{translationValue}-{wordValue}/delete", method = RequestMethod.GET)
	public String wordDelete(@PathVariable String listname,@PathVariable String wordValue,@PathVariable String translationValue, Locale locale, Model model) {

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

		return "redirect:/wordlists/"+listname; 
	}
	
}


