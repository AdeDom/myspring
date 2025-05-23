package com.adedom.myspring.domain.usecase

import com.adedom.myspring.data.repository.TodoRepository
import com.adedom.myspring.domain.exception.ValidateException
import org.springframework.stereotype.Service

interface DeleteTodoUseCase {
    fun execute(id: Int?)
}

@Service
class DeleteTodoUseCaseImpl(
    private val todoRepository: TodoRepository,
) : DeleteTodoUseCase {
    override fun execute(id: Int?) =
        when {
            id == null -> throw ValidateException("Id is null")
            else -> todoRepository.deleteTodo(id)
        }
}
