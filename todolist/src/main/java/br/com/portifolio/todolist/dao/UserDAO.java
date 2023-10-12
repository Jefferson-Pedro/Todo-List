package br.com.portifolio.todolist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portifolio.todolist.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID>{
	public Optional<User> findByuserNameContaining(String key);
}
