package com.jtspringproject.JtSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; // Add this import

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
// Extend SpringBootServletInitializer
public class JtSpringProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JtSpringProjectApplication.class, args);
	}

}
