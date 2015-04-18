package com.lingueco.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WordListRepository extends CrudRepository<WordList, String> {

    
    
    @Query("MATCH (a:WordList {name:{name}}) MATCH (a)-[:OWNS]-(p:Person) RETURN p")
    Iterable<Person> getOwners(@Param("name") String name);
    
    @Query("MATCH (wl:WordList {name:{name}}) RETURN wl")
    WordList getListByName(@Param("name") String name);

    @Query("MATCH (a:WordList {name:{name}}) MATCH (a)-[:WORD]-(p:Word) WHERE p.lang =~ {lang} RETURN p")
    Iterable<Word> getWords(@Param("name") String name, @Param("lang") String lang);
    
    @Query("MATCH (a:WordList) Return a")
    Iterable<WordList> getLists();
}