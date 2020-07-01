package it.dstech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"it.dstech"})
@EnableJpaRepositories(basePackages="it.dstech.repository")
@EnableTransactionManagement
@EntityScan(basePackages="it.dstech.models")
public class Starter {
	
	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}

}
