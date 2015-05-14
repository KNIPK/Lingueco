package com.lingueco.wordlists.dao;

import com.lingueco.wordlists.entity.Word;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends CrudRepository<Word, String> {

	@Query("MATCH (w:Word {name:{name}}) RETURN w")
	Word getWordByName(@Param("name") String name);

	@Query("MATCH (w:Word {value:{name}}) MATCH  (w)<-[:TRANSLATION]-(t:Word)-[:WORD]-(wl:WordList {name:{listName}}) RETURN t")
	Iterable<Word> getTranslations(@Param("name") String name, @Param("listName")String listName);
	
	@Query("MATCH (a:Word {value:{valA}, lang:{langA} }),(b:Word {value:{valB}, lang:{langB}}) CREATE (a)<-[:TRANSLATION]-(b)")
	void createRelTranslation(@Param("valA") String valA, @Param("valB") String valB,
								@Param("langA") String langA, @Param("langB") String langB);
	
	@Query("MATCH (a:Word {value:{valA}}),(b:Word {value:{valB}}) Return EXISTS( (a)-[:TRANSLATION]-(b))")
	boolean isRelatedTranslation(@Param("valA") String valA, @Param("valB") String valB);
	
	@Query("MATCH (a:Word {value:{val}}) DELETE a")
	void deleteNode(@Param("val")String wordValue, @Param("lang")String lang);
	
	@Query("MATCH (a:Word {value:{val}})-[r]-() DELETE a,r")
	void deleteNodeWithRelations(@Param("val")String wordValue, @Param("lang")String lang);
	
	@Query("MATCH (a:Word {value:{translationValue}})-[relW:WORD]-(wl:WordList {name:{listName}}) DELETE relW")
	void removeWordFromList(@Param("translationValue")String translationValue, @Param("listName")String listName);
	
	@Query("MATCH (w:Word {value:{val}})-[relT:TRANSLATION]-(t:Word) MATCH (w)-[relW:WORD]-(wl:WordList {name:{listName}}) Delete relW,relT")
	void deleteWord(@Param("val")String wordValue, @Param("listName")String listName);

	@Query("MATCH (a:Word {value:{val}})-[r:WORD]-(wl:Wordlist {name:{listName}}) DELETE r")
	void removeWordRelation(@Param("val")String wordValue, @Param("listName")String listName);

	@Query("MATCH (a:Word {value:{val}})-[rel:WORD]-(wl:WordList) RETURN count(rel)")
	int getWordRelationsNumber(@Param("val")String wordValue);
	

}