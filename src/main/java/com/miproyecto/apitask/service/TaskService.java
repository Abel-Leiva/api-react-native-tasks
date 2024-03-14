package com.miproyecto.apitask.service;

import com.miproyecto.apitask.entity.Task;
import com.miproyecto.apitask.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    //crear tarea
   public void saveTask(Task task){

       taskRepository.save(task);


   }
//actualizar tarea
    public String UpdateTask(Task task) {
        try {
            taskRepository.save(task);
            return "Tarea actualizada exitosamente";
        } catch (Exception ex) {
            ex.printStackTrace(); // O utiliza un sistema de log adecuado
            return "Error al intentar actualizar la tarea";
        }
    }

//Listar Tareas
    public List<Task> getAllTask() {
        try {
            return taskRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al intentar obtener la lista de tareas", ex);
        }
    }

//Traer tarea por id
public Optional<Task> getByIdTask(Long id) {
    try {
        return taskRepository.findById(id);
    } catch (Exception ex) {
        ex.printStackTrace();

    }
    return null;
}



//elimanr tarea
public String deleteTask(Long taskId) {
    try {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            taskRepository.deleteById(taskId);
            return "tarea eliminada exitosamente";
        } else {
            return "No se encontró una tarea con el ID proporcionado";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "Error al intentar eliminar la tarea";
    }
}


}
