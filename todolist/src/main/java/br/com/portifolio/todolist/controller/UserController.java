package br.com.portifolio.todolist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portifolio.todolist.model.User;
import br.com.portifolio.todolist.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	IUserService service;
	
	@PostMapping("/create")
	public ResponseEntity<User> create (@RequestBody User user){
			Optional<User> res = service.findByUserName(user.getUsername());
			if (res.isPresent()) {
				//Ver uma forma de retornar msg no Postman
				System.err.println("O nome de usuário já existe está em uso.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			service.save(user);
			return ResponseEntity.ok(user);
		}

}
