package com.lingueco.wordlists.dao;

import com.lingueco.wordlists.entity.Word;
import com.lingueco.wordlists.entity.WordList;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WordListRepository extends CrudRepository<WordList, String> {

	//Jak na razie wypisuje wszystkie listy. Potem uwzglednimy uzytkownikow
    @Query("MATCH (a:WordList) Return a ORDER BY a.name")
    Iterable<WordList> getListsByUser();

    @Query("MATCH (wl:WordList {name:{name}}) RETURN wl")
    WordList getListByName(@Param("name") String name);

    @Query("MATCH (a:WordList {name:{name}}) MATCH (a)-[:WORD]-(p:Word) WHERE p.lang =~ {lang} RETURN p")
    Iterable<Word> getWords(@Param("name") String name, @Param("lang") String lang);
    
	@Query("MATCH (w:Word {lang:{lang}})-[:WORD]-(:WordList {name:{name}}) RETURN count(w)")
	int getWordsAmount(@Param("name")String name,@Param("lang")String lang);
	
    @Query("MATCH (wl:WordList {name:{name}}) SET wl.name={newName}, wl.desc={newDesc}")
	void editWordList(@Param("name")String name, @Param("newName")String newName,@Param("newDesc")String newDesc);
    
}