package com.example.kotlin_spring_restapi.service

import com.example.kotlin_spring_restapi.entity.Task

interface TaskService {
    fun findAllTasks(): ArrayList<Task>
    fun findTaskById(id: Long): Task
    fun createTask(task: Task)
    fun updateTask(task: Task)
    fun deleteTaskById(id: Long)
}