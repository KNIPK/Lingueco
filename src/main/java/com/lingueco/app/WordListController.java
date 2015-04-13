package com.lingueco.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lingueco.repository.Person;
import com.lingueco.repository.Word;
import com.lingueco.repository.WordList;
import com.lingueco.repository.WordListRepository;
import com.lingueco.repository.WordRepository;

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

	private static final Logger logger = LoggerFactory
			.getLogger(MainController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Main wordlists page");

		return this.viewPath + "main";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addWord(Locale locale, Model model) {
		logger.info("Add new wordlist (GET)");

		return this.viewPath + "addList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addList(@RequestParam String name,
			@RequestParam String langA, @RequestParam String langB,
			@RequestParam String desc, Locale locale, Model model) {
		logger.info("Add new wordlist from form (POST)");

		Transaction tx = graphDatabase.beginTx();
		WordList wl = new WordList(name, desc, langA, langB);

		try {

			wordListRepository.save(wl);
			
			tx.success();
		} catch (Exception e) {
			tx.failure();
			System.out.println(e);
		} finally {
			tx.close();
		}
		System.out.println(wl.getName() + " created!");
		model.addAttribute("wordlist", wl);
		model.addAttribute("add", new String("added"));
		return this.viewPath + "listView";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String listView(@PathVariable String name, Locale locale, Model model) {
		logger.info("List view (" + name + ")");

		Transaction tx = graphDatabase.beginTx();
		WordList wl = new WordList();
		try {
			wl = wordListRepository.getListByName(name);
			tx.success();
		} catch (Exception e) {
			tx.failure();
			System.out.println(e);
		} finally {
			tx.close();
		}
		System.out.println(wl.getName() + " found!");

		model.addAttribute("wordlist", wl);
		return this.viewPath + "listView";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public String addWord(@RequestParam String name,
			@RequestParam String valA, @RequestParam String valB,
			Locale locale, Model model) {
		logger.info("Add word (" + valA + ") to list " + name + "!");

		Transaction tx = graphDatabase.beginTx();
		
		WordList wl = new WordList();
		Word w = new Word();
		Word t = new Word();
		List<Word> words = new ArrayList<Word>();
		
		try {
			wl = wordListRepository.getListByName(name);
			w = new Word(valA, wl.getLangA());
			t = new Word(valB, wl.getLangB());

			wordRepository.save(w);
			wordRepository.save(t);
			wordRepository.createRelTranslation(valA, valB, wl.getLangA(), wl.getLangB());
			
			wl.word(w);
			wl.word(t);

			wordListRepository.save(wl);
			
			for(Word gw: wordListRepository.getWords(name,wl.getLangA())) {
				words.add(gw);
			}	
			
			
			tx.success();
		} catch (Exception e) {
			tx.failure();
			System.out.println(e);
		} finally {
			tx.close();
		}
		
		System.out.println(wl.getName() + " found! Word ("+w.getValue()+") added! ");
		
		model.addAttribute("wordlist", wl);
		model.addAttribute("words", words);
		
		return this.viewPath + "listView";
	}

}
