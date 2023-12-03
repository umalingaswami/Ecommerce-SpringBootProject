package com.jtspringproject.JtSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, SecurityAutoConfiguration.class })
public class JtSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtSpringProjectApplication.class, args);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

	}

}
