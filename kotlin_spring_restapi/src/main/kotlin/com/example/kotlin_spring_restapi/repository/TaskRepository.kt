package com.example.kotlin_spring_restapi.repository

import com.example.kotlin_spring_restapi.entity.Task
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

interface TaskRepository : Repository<Task, Long> {
    @Query("SELECT * FROM tasks")
    fun findAllTasks(): ArrayList<Task>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun findTaskById(@Param("id") id: Long): Task

    @Modifying
    @Query("INSERT INTO tasks (name) VALUES (:name)")
    fun createTask(@Param("name") name: String)

    @Modifying
    @Query("UPDATE tasks SET name = :name WHERE id = :id")
    fun updateTask(@Param("id") id: Long, @Param("name") name: String)

    @Modifying
    @Query("DELETE FROM tasks WHERE id = :id")
    fun deleteTaskById(@Param("id") id: Long)
}