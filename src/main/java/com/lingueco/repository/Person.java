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
public class Person {

    @GraphId 
    public Long id;
    
    @Indexed(unique = true)
    public String name;

	public Person() {}
    public Person(String name) { this.name = name; }

    @RelatedTo(type="TEAMMATE", direction=Direction.BOTH)
    public @Fetch Set<Person> teammates;
    
    @RelatedTo(type="FRIEND", direction=Direction.OUTGOING)
    public @Fetch Set<Person> friends;
    
    @RelatedTo(type="USE", direction=Direction.OUTGOING)
    public @Fetch Set<Tool> usedTools;

    public void worksWith(Person person) {
        if (teammates == null) {
            teammates = new HashSet<Person>();
        }
        teammates.add(person);
    }
    
    public void likes(Person person) {
    	if(friends == null) {
    		friends = new HashSet<Person>();
    	}
    	friends.add(person);
    }
    
    public void use(Tool tool) {
    	if(usedTools == null) {
    		usedTools = new HashSet<Tool>();
    	}
    	usedTools.add(tool);
    }

    public String toString() {
        String results = name + "'s teammates include\n";
        if (teammates != null) {
            for (Person person : teammates) {
                results += "\t- " + person.name + "\n";
            }
        }
        return results;
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
  	public Set<Person> getTeammates() {
  		return teammates;
  	}
  	public void setTeammates(Set<Person> teammates) {
  		this.teammates = teammates;
  	}
  	public Set<Person> getFriends() {
  		return friends;
  	}
  	public void setFriends(Set<Person> friends) {
  		this.friends = friends;
  	}
  	public Set<Tool> getUsedTools() {
  		return usedTools;
  	}
  	public void setUsedTools(Set<Tool> usedTools) {
  		this.usedTools = usedTools;
  	}

}
