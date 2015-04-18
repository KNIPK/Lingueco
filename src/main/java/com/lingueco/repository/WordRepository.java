package com.lingueco.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends CrudRepository<Word, String> {

	@Query("MATCH (w:Word {name:{name}}) RETURN w")
	Word getWordByName(@Param("name") String name);

	@Query("MATCH (w:Word {value:{name}}) MATCH  (w)<-[:TRANSLATION]-(t:Word {lang:{lang}}) RETURN t")
	//@Query("MATCH (a:Word {value:{name}}) RETURN (a)<-[:TRANSLATION]-()")
	Iterable<Word> getTranslations(@Param("name") String name, @Param("lang")String language);
	
	@Query("MATCH (a:Word {value:{valA}, lang:{langA} }),(b:Word {value:{valB}, lang:{langB}}) CREATE (a)<-[:TRANSLATION]-(b)")
	void createRelTranslation(@Param("valA") String valA, @Param("valB") String valB,
								@Param("langA") String langA, @Param("langB") String langB);
	
	@Query("MATCH (a:Word {value:{valA}}),(b:Word {value:{valB}}) Return EXISTS( (a)-[:TRANSLATION]-(b))")
	boolean isRelatedTranslation(@Param("valA") String valA, @Param("valB") String valB);
}