package com.adedom.myspring.data.model.todo

data class TodoEntity(
    val id: Int,
    val title: String?,
)

fun TodoEntity.toResponse(): TodoResponse =
    TodoResponse(
        id = id,
        title = title,
    )
