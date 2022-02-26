package com.example.kotlin_spring_restapi.exception

import com.example.kotlin_spring_restapi.response.TaskResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(ApiBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private fun handleApiBadRequestException(e: ApiBadRequestException): ResponseEntity<TaskResponse> {
        return ResponseEntity.badRequest().body(e.message?.let { TaskResponse("400", it) })
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private fun handleException(e: Exception): ResponseEntity<TaskResponse> {
        return ResponseEntity.badRequest().body(e.message?.let { TaskResponse("500", it) })
    }
}