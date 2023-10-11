package br.com.portifolio.todolist.service;

import java.util.List;
import java.util.UUID;

import br.com.portifolio.todolist.model.User;

public interface IUserService {
	
	public User save(User novo);
	public User update(User user, UUID id);
	public boolean delete(UUID id);
	public List<User> findAll();
	public User findById(UUID id);
}
