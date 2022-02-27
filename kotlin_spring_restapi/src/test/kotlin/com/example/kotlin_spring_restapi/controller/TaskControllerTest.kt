package com.example.kotlin_spring_restapi.controller

import com.example.kotlin_spring_restapi.entity.Task
import com.example.kotlin_spring_restapi.service.TaskService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@ExtendWith(SpringExtension::class)
@WebMvcTest
class TaskControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: TaskService

    @Test
    fun `findAllTasks should work successfully`() {
        every { service.findAllTasks() } returns arrayListOf(Task(1, "hello"), Task(2, "world"))
        mockMvc.get("/tasks") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("[{\"id\": 1, \"name\": \"hello\"}, {\"id\": 2, \"name\": \"world\"}]") }
        }
    }

    @Test
    fun `findTaskById should work successfully`() {
        every { service.findTaskById(any()) } returns Task(1, "hello")
        mockMvc.get("/tasks/1") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{\"id\": 1, \"name\": \"hello\"}") }
        }
    }

    @Test
    fun `createTask should work successfully`() {
        justRun { service.createTask(any()) }
        mockMvc.post("/tasks") {
            contentType = MediaType.APPLICATION_JSON
            param("name", "hoge")
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{\"code\": \"200\", \"message\": \"success\"}") }
        }
    }

    // TODO: Check all exceptions regarding API requests
    @Test
    fun `createTask should return ApiBadRequestException successfully`() {
        justRun { service.createTask(any()) }
        mockMvc.post("/tasks") {
            contentType = MediaType.APPLICATION_JSON
            param("name", "")
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    fun `updateTaskById should work successfully`() {
        justRun { service.updateTask(any()) }
        mockMvc.put("/tasks/{id}", "1") {
            contentType = MediaType.APPLICATION_JSON
            param("name", "hoge")
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{\"code\": \"200\", \"message\": \"success\"}") }
        }
    }

    @Test
    fun `deleteTaskById should work successfully`() {
        justRun { service.deleteTaskById(any()) }
        mockMvc.delete("/tasks/{id}", "1") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{\"code\": \"200\", \"message\": \"success\"}") }
        }
    }
}