package com.adedom.myspring.domain.usecase

import com.adedom.myspring.data.model.todo.TodoRequest
import com.adedom.myspring.data.repository.TodoRepository
import com.adedom.myspring.domain.exception.ValidateException
import org.springframework.stereotype.Service

interface UpdateTodoUseCase {
    fun execute(
        id: Int?,
        request: TodoRequest,
    )
}

@Service
class UpdateTodoUseCaseImpl(
    private val todoRepository: TodoRepository,
) : UpdateTodoUseCase {
    override fun execute(
        id: Int?,
        request: TodoRequest,
    ) = when {
        id == null -> throw ValidateException("Id is null")
        request.title.isNullOrBlank() -> throw ValidateException("Title is null or blank")
        else -> todoRepository.updateTodo(id, request.title)
    }
}
