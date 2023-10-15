package br.com.portifolio.todolist.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portifolio.todolist.model.Task;


@Repository
public interface ITaskDAO extends JpaRepository<Task, UUID> {
    public Optional<Task> findBytitleContaining(String key);
    public List<Task> findByidUser(UUID idUser);
    public Task findByIdUser(UUID idUser);
}
