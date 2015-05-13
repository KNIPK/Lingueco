package com.lingueco.configuration;
import javax.annotation.Resource;

import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;


@Configuration
@PropertySource(value="classpath:/properties/database.properties")
@EnableNeo4jRepositories(basePackages="com.lingueco")
public class Neo4jConfig extends Neo4jConfiguration {
	
	@Resource
	public Environment env;

	public Neo4jConfig() {
		setBasePackage("com.lingueco");
	}
	
    @Bean
    public SpringRestGraphDatabase graphDatabaseService() {
    	
    	return new SpringRestGraphDatabase(env.getProperty("db.location"), env.getProperty("db.user"), env.getProperty("db.password"));    	
    }
    
}