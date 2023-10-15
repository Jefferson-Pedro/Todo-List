package br.com.portifolio.todolist.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portifolio.todolist.model.Task;
import br.com.portifolio.todolist.service.ITaskService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    ITaskService service;

    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody Task task, HttpServletRequest request) {
       	
        if(task != null){
            Object idUser = request.getAttribute("idUser");
            task.setIdUser((UUID)idUser);
            if (service.save(task)){
                return ResponseEntity.ok(task);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }
        System.err.println("O corpo da tarefa está vazio!");
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Task>> findAll(){
       List<Task> list = service.findAll();
       if(list.size() > 0){
        return ResponseEntity.ok(list);
       }
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/listTaskUser")
    public ResponseEntity<List<Task>> findByIdUser(HttpServletRequest request){
        Object idUser = request.getAttribute("idUser");

        System.out.println(" >>>>>>>>>>>>>>>>>>>> Id do Usuário: " + idUser);
        
        List<Task> list = service.findByIdUser((UUID)idUser);
        if(list.size() > 0){
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.badRequest().build();
    }
    
}
