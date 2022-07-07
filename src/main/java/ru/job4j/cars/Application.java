package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {

	@Bean(destroyMethod = "close")
	public SessionFactory sf() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
