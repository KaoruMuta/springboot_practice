package com.example.kotlin_spring_restapi.entity

import javax.validation.constraints.Size

data class Task(
    val id: Long = 0,
    @field:Size(min = 1, max = 255)
    val name: String
)
