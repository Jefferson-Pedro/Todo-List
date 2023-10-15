package br.com.portifolio.todolist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.portifolio.todolist.dao.ITaskDAO;
import br.com.portifolio.todolist.model.Task;


@Component
public class TaskImpl implements ITaskService {

    @Autowired
	private ITaskDAO dao;

    @Override
    public boolean save(Task novo) {
        LocalDateTime date = LocalDateTime.now();
        //if(date.isAfter(novo.getStartAt())) - Maior que a data atual

        //Verifica se a data de inicio e fim são maiores que a data atual
        if (date.isAfter(novo.getStartAt()) || date.isAfter(novo.getEndAt())) {
            System.err.println("A data de início/término deve ser maior que a atual");
            return false;
        }

        //Verifica se a data de inicio é maior que a data de fim
        if (novo.getStartAt().isAfter(novo.getEndAt()) ) {
            System.err.println("A data de início deve ser menor que a data de término");
            return false;
        }
        dao.save(novo);
        return true;
    }

    @Override
    public Task update(Task task, UUID id) {
        Optional<Task> res = dao.findById(id);
	    if (res.isPresent()) {
	    	Task existingTask = res.get();
	        BeanUtils.copyProperties(task, existingTask, "id");
	        return dao.save(existingTask);
	    }
	    System.err.println("Erro ao editar o usuário!");
		return null;
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Task> res = dao.findById(id);
	    if (res.isPresent()) {
	    	dao.deleteById(id);
	    	System.out.println("Tarefa com id " + id + " excluido com sucesso!");
	    	return true;
	    }
	    System.err.println("Ocorreu um erro ao excluir o usuário " + id);
		return false;
    }

    @Override
    public List<Task> findAll() {
       return dao.findAll();
    }

    @Override
    public Task findById(UUID id) {
       return dao.findById(id).orElse(null);
    }

    @Override
    public Optional<Task> findByTaskTitle(String taskName) {
        return dao.findBytitleContaining(taskName);
    }

    @Override
    public List<Task> findByIdUser(UUID idUser) {
        return dao.findByidUser((UUID)idUser);
    }
}
