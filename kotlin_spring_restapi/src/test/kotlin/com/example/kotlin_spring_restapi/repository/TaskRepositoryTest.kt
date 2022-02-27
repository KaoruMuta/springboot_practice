package com.example.kotlin_spring_restapi.repository

import com.example.kotlin_spring_restapi.entity.Task
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJdbcTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {
    @Autowired
    private lateinit var repository: TaskRepository

    @Test
    fun `findAllTasks should work successfully`() {
        val tasks = repository.findAllTasks()
        assert(tasks.count() == 4)
        assert(tasks[0] == Task(1, "Kotlinの導入検討"))
        assert(tasks[1] == Task(2, "APIのエンドポイント設計"))
        assert(tasks[2] == Task(3, "DB設計"))
        assert(tasks[3] == Task(4, "アクセス負荷対応"))
    }

    @Test
    fun `findTaskById should work successfully`() {
        assert(repository.findTaskById(1) == Task(1, "Kotlinの導入検討"))
        assert(repository.findTaskById(2) == Task(2, "APIのエンドポイント設計"))
        assert(repository.findTaskById(3) == Task(3, "DB設計"))
        assert(repository.findTaskById(4) == Task(4, "アクセス負荷対応"))
    }

    @Test
    fun `createTask should work successfully`() {
        repository.createTask("hoge")
        val task = repository.findAllTasks().last()
        assert(task.name == "hoge")
    }

    @Test
    fun `updateTaskById should work successfully`() {
        repository.updateTask(1, "hoge")
        val task = repository.findTaskById(1)
        assert(task.name == "hoge")
    }

    @Test
    fun `deleteTaskById should work successfully`() {
        val tasks = repository.findAllTasks()
        repository.deleteTaskById(tasks.lastIndex.toLong())
        val expected = repository.findAllTasks()
        assert(expected.count() == tasks.count() - 1)
    }
}