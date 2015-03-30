package com.lingueco.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, String> {

    Person findByName(String name);

    Iterable<Person> findByTeammatesName(String name);
    
    Iterable<Person> findByFriendsName(String name);
    
    @Query("MATCH (p {name: {name}}) MATCH (p)-[:USE]-(t:Tool) RETURN t")
    /* or:
     * @Query("MATCH (p {name: {0}}) MATCH (p)-[:USE]-(t:Tool) RETURN t") - without @Param annotation
     */
    Iterable<Tool> getUsedTools(@Param("name") String name);
    

}