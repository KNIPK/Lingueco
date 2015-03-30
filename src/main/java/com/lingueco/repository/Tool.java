package com.lingueco.repository;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Tool {
	@GraphId public Long id;
	
	public String name;
	
	public Tool() {}
	
	public Tool(String name) {
		this.name = name;
	}

	 public String toString() {
		 return name;
	 }
}
