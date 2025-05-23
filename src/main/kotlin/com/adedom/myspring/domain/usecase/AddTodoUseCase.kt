package com.adedom.myspring.domain.usecase

import com.adedom.myspring.data.model.todo.TodoRequest
import com.adedom.myspring.data.repository.TodoRepository
import com.adedom.myspring.domain.exception.ValidateException
import org.springframework.stereotype.Service

interface AddTodoUseCase {
    fun execute(request: TodoRequest)
}

@Service
class AddTodoUseCaseImpl(
    private val todoRepository: TodoRepository,
) : AddTodoUseCase {
    override fun execute(request: TodoRequest) =
        when {
            request.title.isNullOrBlank() -> throw ValidateException("Title is null or blank")
            else -> todoRepository.addTodo(request)
        }
}
