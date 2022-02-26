package com.example.kotlin_spring_restapi.controller

import com.example.kotlin_spring_restapi.entity.Task
import com.example.kotlin_spring_restapi.response.TaskResponse
import com.example.kotlin_spring_restapi.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(private val service: TaskService) {
    @GetMapping
    private fun findAllTasks(): ArrayList<Task> {
        return service.findAllTasks()
    }

    @GetMapping("/{id}")
    private fun findTaskById(@PathVariable("id") id: Long): Task {
        return service.findTaskById(id)
    }

    @PostMapping
    private fun createTask(@Validated task: Task): ResponseEntity<TaskResponse> {
        service.createTask(task)
        return ResponseEntity.ok(TaskResponse("200", "success"))
    }

    @PutMapping("/{id}")
    private fun updateTask(@PathVariable("id") id: Long, @Validated task: Task): ResponseEntity<TaskResponse> {
        service.updateTask(Task(id, task.name))
        return ResponseEntity.ok(TaskResponse("200", "success"))
    }

    @DeleteMapping("/{id}")
    private fun deleteTask(@PathVariable("id") id: Long): ResponseEntity<TaskResponse> {
        service.deleteTaskById(id)
        return ResponseEntity.ok(TaskResponse("200", "success"))
    }
}