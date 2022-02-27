package com.example.kotlin_spring_restapi.service

import com.example.kotlin_spring_restapi.entity.Task
import com.example.kotlin_spring_restapi.repository.TaskRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ExtendWith(MockKExtension::class)
class TaskServiceTest {
    @MockkBean(relaxUnitFun = true)
    private lateinit var repository: TaskRepository

    private lateinit var service: TaskService

    @BeforeEach
    fun setup() {
        service = TaskServiceImpl(repository)
    }

    @Test
    fun `findAllTasks should work successfully`() {
        every { repository.findAllTasks() } returns arrayListOf(Task(1, "hello"), Task(2, "world"))
        val expected = service.findAllTasks()
        assert(expected.count() == 2)
        assert(expected[0] == Task(1, "hello"))
        assert(expected[1] == Task(2, "world"))
    }

    @Test
    fun `findTaskById should work successfully`() {
        every { repository.findTaskById(1) } returns Task(1, "hello")
        val expected = service.findTaskById(1)
        assert(expected == Task(1, "hello"))
    }

    @Test
    fun `createTask should work successfully`() {
        service.createTask(Task(1, "hoge"))
        verify { repository.createTask("hoge") }
        confirmVerified(repository)
    }

    @Test
    fun `updateTask should work successfully`() {
        service.updateTask(Task(1, "hoge"))
        verify { repository.updateTask(1, "hoge") }
        confirmVerified(repository)
    }

    @Test
    fun `deleteTaskById should work successfully`() {
        service.deleteTaskById(1)
        verify { repository.deleteTaskById(1) }
        confirmVerified(repository)
    }
}