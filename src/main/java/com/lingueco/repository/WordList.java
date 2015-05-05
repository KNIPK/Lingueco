package com.lingueco.repository;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class WordList {

	@GraphId
	public Long id;

	@Indexed(unique = true)
	public String name;

	public String desc;

	public String langA;

	public String langB;

	public int privacy;
	// 0 - private, 1 - public

	public int wordsAmount;

	public double learningRate;
	
	@RelatedTo(type = "WORD", direction = Direction.OUTGOING)
	public @Fetch Set<Word> words;

	public WordList() {
	}

	public WordList(String name, String desc, String langA, String langB) {
		this.name = name;
		this.desc = desc;
		this.langA = langA;
		this.langB = langB;
	}

	public void word(Word w) {
		if (words== null) {
			words = new HashSet<Word>();
		}
		words.add(w);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLangA() {
		return langA;
	}

	public void setLangA(String langA) {
		this.langA = langA;
	}

	public String getLangB() {
		return langB;
	}

	public void setLangB(String langB) {
		this.langB = langB;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public int getWordsAmount() {
		return wordsAmount;
	}

	public void setWordsAmount(int wordsAmount) {
		this.wordsAmount = wordsAmount;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "WordList [id="
				+ id + ", name=" + name + ", desc=" + desc + ", langA=" + langA
				+ ", langB=" + langB + ", privacy=" + privacy
				+ ", wordsAmount=" + wordsAmount + ", learningRate="
				+ learningRate + ", words=" + words + "]";
	}



}
