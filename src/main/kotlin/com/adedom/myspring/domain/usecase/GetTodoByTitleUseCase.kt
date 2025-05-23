package com.adedom.myspring.domain.usecase

import com.adedom.myspring.data.model.todo.TodoResponse
import com.adedom.myspring.data.repository.TodoRepository
import com.adedom.myspring.domain.exception.ValidateException
import org.springframework.stereotype.Service

interface GetTodoByTitleUseCase {
    fun execute(title: String?): TodoResponse
}

@Service
class GetTodoByTitleUseCaseImpl(
    private val todoRepository: TodoRepository,
) : GetTodoByTitleUseCase {
    override fun execute(title: String?): TodoResponse {
        val title =
            title?.takeIf { it.isNotBlank() }
                ?: throw ValidateException("Title is null or blank")
        return todoRepository.getTodoByTitle(title)
    }
}
