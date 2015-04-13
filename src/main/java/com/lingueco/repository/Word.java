package com.lingueco.repository;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Word {

	@GraphId
	public Long id;

	@Indexed(unique = true)
	public String value;	
	public String lang;


	public double learningRate;

	@RelatedTo(type = "WORD", direction = Direction.INCOMING)
	public @Fetch Set<WordList> lists;
	
	@RelatedTo(type = "TRANSLATION", direction = Direction.BOTH)
	public @Fetch Set<Word> translations;

	public Word() {
	}

	public Word(String value, String lang) {
		this.value = value;
		this.lang = lang;
	}

	public void list(WordList wl) {
		if (lists == null) {
			lists = new HashSet<WordList>();
		}
		lists.add(wl);
	}
	public void translation(Word w) {
		if (translations == null) {
			translations = new HashSet<Word>();
		}
		translations.add(w);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public Set<WordList> getLists() {
		return lists;
	}

	public void setLists(Set<WordList> lists) {
		this.lists = lists;
	}

	public Set<Word> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<Word> translations) {
		this.translations = translations;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", value=" + value + ", lang=" + lang
				+ ", learningRate=" + learningRate + ", lists=" + lists
				+ ", translations=" + translations + "]";
	}
	
	
}