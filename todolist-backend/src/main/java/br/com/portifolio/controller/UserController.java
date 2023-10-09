package br.com.portifolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portifolio.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	public ResponseEntity<User> save (@RequestBody User user){
		return null;
	}
}
