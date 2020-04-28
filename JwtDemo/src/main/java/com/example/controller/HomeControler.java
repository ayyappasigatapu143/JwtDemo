package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.request.SiginRequest;
import com.example.request.SignUpRequest;
import com.example.util.JwtUtil;

@RestController
public class HomeControler {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String  helloWorld(){
		return "welcome to JWT Security";
	}
	
	
	
	@PostMapping("/authenticate")
	public  String signinuser(@RequestBody SiginRequest signinRequest) throws Exception{
		try{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return jwtUtil.generateToken(signinRequest.getUsername());
	}
	
	@PostMapping("/register")
	public User  registeruser(@RequestBody  SignUpRequest signUp) throws Exception {
		 User  users;   
		User user=userRepository.findUserByname(signUp.getUsername()); 
	    if(user !=null) {
	       throw new Exception("User already exists ");	
	    }
	    else {
	      users= userRepository.save(new User(signUp.getUsername(),signUp.getEmail(),signUp.getPassword()))	;
	    }
	    return users;
	}
	
}
