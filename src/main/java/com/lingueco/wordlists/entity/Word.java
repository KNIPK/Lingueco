package com.lingueco.wordlists.entity;

import java.util.Set;

import com.lingueco.wordlists.dao.WordRepository;
import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RelatedTo(type = "WORD", direction = Direction.INCOMING)
	public @Fetch Set<WordList> lists;
	

	public Word() {
	}

	public Word(String value, String lang) {
		this.value = value;
		this.lang = lang;
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

	public Set<WordList> getLists() {
		return lists;
	}

	public void setLists(Set<WordList> lists) {
		this.lists = lists;
	}



	@Override
	public String toString() {
		return "Word [id=" + id + ", value=" + value + ", lang=" + lang
				+ ", learningRate=" + ", lists=" + lists+"]";
	}
	
	
}
