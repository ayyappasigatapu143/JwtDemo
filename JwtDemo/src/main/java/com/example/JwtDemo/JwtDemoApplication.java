package com.example.JwtDemo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.model.User;
import com.example.repository.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages="com.example")
@EntityScan("com.example.model")
@EnableJpaRepositories("com.example.repository")
public class JwtDemoApplication {

	@Autowired
	private UserRepository userRepository;
	@PostConstruct
	public void initUsers(){
		List<User> usersList =Stream.of(new User("ayyappa","ayyapa@gmail.com","Venki@123"),
				new User("venki","venki@gmail.com","venki@123"),
				new User("world","world@gmail.com","worlds@123")).collect(Collectors.toList());
	   userRepository.saveAll(usersList);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

}
