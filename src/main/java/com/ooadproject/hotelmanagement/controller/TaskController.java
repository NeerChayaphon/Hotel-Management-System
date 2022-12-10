package com.ooadproject.hotelmanagement.controller;

import com.ooadproject.hotelmanagement.model.Task;
import com.ooadproject.hotelmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }


    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId){
        return taskRepository.findById(taskId).get();
    }

    @GetMapping("/severity/{severity}")
    public List<Task> findTaskUsingSeverity(@PathVariable int severity){
        return taskRepository.findBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee){
        return taskRepository.getTasksByAssignee(assignee);
    }

    @PutMapping("/{taskId}")
    public Task modifyTask(@RequestBody Task taskRequest, @PathVariable String taskId){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Task existingTask = taskRepository.findById(taskId).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return taskRepository.save(existingTask);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        taskRepository.deleteById(taskId);
        return taskId+" task deleted from dashboard ";
    }
}