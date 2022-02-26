package com.example.kotlin_spring_restapi.service

import com.example.kotlin_spring_restapi.entity.Task
import com.example.kotlin_spring_restapi.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskServiceImpl(private val repository: TaskRepository) : TaskService {
    override fun findAllTasks(): ArrayList<Task> {
        return repository.findAllTasks()
    }

    override fun findTaskById(id: Long): Task {
        return repository.findTaskById(id)
    }

    @Transactional
    override fun createTask(task: Task) {
        repository.createTask(task.name)
    }

    @Transactional
    override fun updateTask(task: Task) {
        repository.updateTask(task.id, task.name)
    }

    @Transactional
    override fun deleteTaskById(id: Long) {
        repository.deleteTaskById(id)
    }
}