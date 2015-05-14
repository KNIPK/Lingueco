package com.lingueco.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.lingueco.wordlists.service.WordListService;
import com.lingueco.wordlists.service.WordService;
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
	WordService wordService;
	@Autowired
	WordListService wordListService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<WordList> wordLists = new ArrayList<WordList>();
		wordLists = wordListService.showLists();

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

		wordListService.addList(name, desc, langA, langB);
		
		return "redirect:/wordlists/"+name; 
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String listView(@PathVariable String name, Locale locale, Model model) {


		WordList wordList = new WordList();
		HashMap<Word, List<Word>> words = new HashMap<Word, List<Word>>();

		wordList = wordListService.getListByName(name);
		words =	wordListService.wordsFromList(wordList,name);

		model.addAttribute("wordlist", wordList);
		model.addAttribute("words", words);
		return this.viewPath + "listView";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public String addWord(@RequestParam String name,
			@RequestParam String valA, @RequestParam String valB,
			Locale locale, Model model) {

		wordService.addWord(name,valA, valB);

		return "redirect:/wordlists/"+name;
		}

	@RequestMapping(value = "/{name}/edit", method = RequestMethod.GET)
	public String listEdit(@PathVariable String name, Locale locale, Model model) {

		WordList wordList = wordListService.getListByName(name);

		model.addAttribute("wordlist", wordList);
		return this.viewPath + "listEdit";
	}
	
	@RequestMapping(value = "/{name}/edit", method = RequestMethod.POST)
	public String listEdit(@RequestParam String oldName,@RequestParam String newName,
							@RequestParam String newDesc, Locale locale, Model model) {

		wordListService.listEdit(oldName,newName,newDesc);

		return "redirect:/wordlists/"+newName; 
	}
	
	@RequestMapping(value = "/{listname}/{translationValue}-{wordValue}/delete", method = RequestMethod.GET)
	public String wordDelete(@PathVariable String listname,@PathVariable String wordValue,@PathVariable String translationValue, Locale locale, Model model) {

		wordService.wordDelete(translationValue,wordValue,listname);

		return "redirect:/wordlists/"+listname; 
	}

}