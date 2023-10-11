package br.com.portifolio.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
		User res = service.findByUserName(user);
		if (user != null && user != res) {
			service.save(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
}
