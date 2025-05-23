package com.adedom.myspring.domain.usecase

import com.adedom.myspring.data.model.todo.TodoResponse
import com.adedom.myspring.data.repository.TodoRepository
import com.adedom.myspring.domain.exception.ValidateException
import org.springframework.stereotype.Service

interface GetTodoByIdUseCase {
    fun execute(id: Int?): TodoResponse
}

@Service
class GetTodoByIdUseCaseImpl(
    private val todoRepository: TodoRepository,
) : GetTodoByIdUseCase {
    override fun execute(id: Int?): TodoResponse {
        val id = id ?: throw ValidateException("Id is null")
        return todoRepository.getTodoById(id)
    }
}
