package com.example.todoapp.Controllers;

import com.example.todoapp.DTO.CreateTaskDTO;
import com.example.todoapp.DTO.EditTaskDTO;
import com.example.todoapp.Enums.TaskStatus;
import com.example.todoapp.Exeptions.NotFoundException;
import com.example.todoapp.Models.Task;
import com.example.todoapp.Repositories.TaskRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TasksController {

    private final TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> all = taskRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid CreateTaskDTO task) {
        Task newTask = Task.builder()
                .name(task.getName())
                .description(task.getDescription())
                .status(TaskStatus.PENDING)
                .build();
        newTask = taskRepository.save(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) throws NotFoundException {

        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new NotFoundException("Task not found");
        }
        return ResponseEntity.ok().body(existingTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody @Valid EditTaskDTO task) throws NotFoundException {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new NotFoundException("Task not found");
        }
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(TaskStatus.valueOf(task.getStatus()));
        existingTask = taskRepository.save(existingTask);
        return ResponseEntity.ok(existingTask);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) throws NotFoundException {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new NotFoundException("Task not found");
        }
        taskRepository.deleteById(existingTask.getId());
        return ResponseEntity.ok().body(existingTask);
    }

}
