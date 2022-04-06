package com.jf.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jf.projectBase.mapper"})
//@Configuration
//@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.jf.projectBase.entity"})
//@EnableJpaRepositories(basePackages = {"com.jf.projectBase.repository"})
public class ProjectBaseApplication{

	public static void main(String[] args) {

		SpringApplication.run(ProjectBaseApplication.class, args);

	}

}
