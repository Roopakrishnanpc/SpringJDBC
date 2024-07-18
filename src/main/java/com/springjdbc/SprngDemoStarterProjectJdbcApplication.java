package com.springjdbc;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springjdbc.model.SpringJDBCDemo;
import com.springjdbc.repository.SpringJDBCRepoDAO;

@SpringBootApplication
public class SprngDemoStarterProjectJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SprngDemoStarterProjectJdbcApplication.class, args);
		SpringJDBCDemo obj=context.getBean(SpringJDBCDemo.class);
		obj.setId(1);
		obj.setName("Roopa");
		obj.setPosition("Senior Software Developer");
		
		SpringJDBCRepoDAO repo=context.getBean(SpringJDBCRepoDAO.class);
		//repo.save(obj);
		//System.out.println(repo.findAll());
		//repo.deleteById(1);
		System.out.println(repo.findById(2));
		//Optional
        Optional<SpringJDBCDemo> demoOptional = repo.findByIdAnotherWay(2);

        demoOptional.ifPresentOrElse(
            demo -> {
                // If present, handle the result
                System.out.println("Found: " + demo);
            },
            () -> {
                // If not present, handle the absence
                System.out.println("No record found with ID: " + 2);
            }
        );
	}

}
