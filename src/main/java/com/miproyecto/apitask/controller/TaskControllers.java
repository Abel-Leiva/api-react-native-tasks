package com.miproyecto.apitask.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.miproyecto.apitask.entity.Task;
import com.miproyecto.apitask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/task")
public class TaskControllers {
    @Autowired
    TaskService taskService;

    //create
    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        try {
            taskService.saveTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tarea creada exitosamente");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la tarea: " + e.getMessage());
        }
    }

    //Listar
    @GetMapping("/list")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> tasks = taskService.getAllTask();
            return new ResponseEntity<>(tasks, HttpStatus.OK);

        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }

    //Actualizar
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@RequestBody Task task, @PathVariable Long id) {
        task.setId(id);
        if (task == null || task.getName() == null || task.getName().isEmpty() || task.getDescription() == null) {
            return new ResponseEntity<>("Error: Se deben completar todos los datos para modificar la tarea", HttpStatus.BAD_REQUEST);
        }
        try {
            taskService.saveTask(task);
            return new ResponseEntity<>("Tarea modificada con exito", HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("error del servidor al modificar tarea", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Traer una tarea por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id) {
        try {

            Optional<Task> task = taskService.getByIdTask(id);
            return new ResponseEntity<>(task, HttpStatus.OK);


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Eliminar tarea
    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            String result = taskService.deleteTask(id);
            return new ResponseEntity<>(result, HttpStatus.OK);


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Error al intentar elimnar la tarea", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
