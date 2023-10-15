package br.com.portifolio.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.portifolio.todolist.model.Task;

public interface ITaskService {

    public boolean save(Task novo);
	public Task update(Task task, UUID id);
	public boolean delete(UUID id);
	public List<Task> findAll();
	public Task findById(UUID id);
	public List<Task> findByIdUser(UUID idUser);
	public Optional<Task> findByTaskTitle(String taskName);
}
