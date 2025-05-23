package com.adedom.myspring.domain.usecase

import com.adedom.myspring.data.model.todo.TodoResponse
import com.adedom.myspring.data.repository.TodoRepository
import org.springframework.stereotype.Service

interface GetTodoAllUseCase {
    fun execute(): List<TodoResponse>
}

@Service
class GetTodoAllUseCaseImpl(
    private val todoRepository: TodoRepository,
) : GetTodoAllUseCase {
    override fun execute(): List<TodoResponse> = todoRepository.getTodos()
}
