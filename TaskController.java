package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    //Constructor-based injection
    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    //Get all tasks
    @GetMapping()
    public ResponseEntity<Page<Task>> getAllTasks(Pageable pageable) {
        Page<Task> tasksPage = taskRepository.findAll(pageable);  // Use Pageable for pagination
        return ResponseEntity.ok(tasksPage);
    }
    
    //Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    //Get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getByPriority(@PathVariable String priority) {
        List<Task> tasks = this.taskRepository.findByPriority(priority);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build(); //Return 204 no content if no tasks found
        }
        return ResponseEntity.ok(tasks); //Return tasks
    }
    
    //Get tasks by deadline
    @GetMapping("/deadline/{deadline}")
    public ResponseEntity<List<Task>> getByDeadline(@PathVariable @DateTimeFormat(pattern = "yyyy/mm/dd") LocalDate deadline) {
        List<Task> tasks = this.taskRepository.findByDeadline(deadline);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build(); //Return 204 no content if no tasks are found
        }
        return ResponseEntity.ok(tasks); //Return 200 OK with the list of tasks
    }
    

    //Create new task
    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task newTask = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    //Update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
        Optional<Task> updateTaskOpt = this.taskRepository.findById(id);
        if (updateTaskOpt.isPresent()) {
            Task existingTask = updateTaskOpt.get();
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDeadline(updatedTask.getDeadline());
            existingTask.setCompleted(updatedTask.isCompleted());
            this.taskRepository.save(existingTask);
            return ResponseEntity.ok(existingTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> deleteTaskOpt = this.taskRepository.findById(id);
        if (deleteTaskOpt.isPresent()) {
            this.taskRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Successful deletion
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if task does not exist
        }
    }
}
