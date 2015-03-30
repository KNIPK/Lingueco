package com.lingueco.app;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lingueco.repository.Person;
import com.lingueco.repository.PersonRepository;
import com.lingueco.repository.Tool;

@Controller
@RequestMapping("/test")
public class TestController {
	
	
	@Autowired PersonRepository personRepository;

	@Autowired GraphDatabase graphDatabase;
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public ModelAndView run() {
		ModelAndView model = new ModelAndView("testController/test");
		
		Transaction tx = graphDatabase.beginTx();
		Person tomek = new Person("Tomek");
		Person jurek = new Person("Jurek");
		Person zbyszek = new Person("Zbyszek");
		Person maciek = new Person("Maciek");
		
		tomek.worksWith(jurek);
		jurek.worksWith(maciek);
		
		zbyszek.likes(tomek);
		zbyszek.likes(maciek);
		
		maciek.likes(jurek);
		
		List<Tool> tools = new ArrayList<Tool>();
		
		tomek.use(new Tool("Plecak"));
		jurek.use(new Tool("Laptop"));
		zbyszek.use(new Tool("Łopata"));
		
		try {

			personRepository.save(tomek);
			personRepository.save(jurek);
			personRepository.save(zbyszek);
			personRepository.save(maciek);

			for(Tool tool : personRepository.getUsedTools("Tomek")) {
				tools.add(tool);
			}	
			tx.success();
		} catch(Exception e) {
			tx.failure();
			System.out.println(e);
		} finally {
			tx.close();
		}
		
		model.addObject("tomek", tomek);
		
		return model;		
	}		
}
