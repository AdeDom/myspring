package com.adedom.myspring.data.repository

import com.adedom.myspring.data.exception.TodoNotFoundException
import com.adedom.myspring.data.model.todo.TodoEntity
import com.adedom.myspring.data.model.todo.TodoRequest
import com.adedom.myspring.data.model.todo.TodoResponse
import com.adedom.myspring.data.model.todo.toResponse
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicInteger

interface TodoRepository {
    fun getTodos(): List<TodoResponse>

    fun getTodoById(id: Int): TodoResponse

    fun getTodoByTitle(title: String): TodoResponse

    fun addTodo(request: TodoRequest)

    fun updateTodo(
        id: Int,
        request: TodoRequest,
    )

    fun deleteTodo(id: Int)
}

@Repository
class TodoRepositoryImpl : TodoRepository {
    private val id = AtomicInteger()
    private val todos = mutableListOf<TodoEntity>()

    override fun getTodos(): List<TodoResponse> = todos.map { it.toResponse() }

    override fun getTodoById(id: Int): TodoResponse =
        todos
            .firstOrNull { it.id == id }
            ?.toResponse()
            ?: throw TodoNotFoundException("Todo Not found")

    override fun getTodoByTitle(title: String): TodoResponse =
        todos
            .firstOrNull { it.title == title }
            ?.toResponse()
            ?: throw TodoNotFoundException("Todo Not found")

    override fun addTodo(request: TodoRequest) {
        val todo =
            TodoEntity(
                id = id.andIncrement,
                title = request.title,
            )
        todos.add(todo)
    }

    override fun updateTodo(
        id: Int,
        request: TodoRequest,
    ) {
        val index = todos.indexOfFirst { it.id == id }

        if (index == -1) {
            throw TodoNotFoundException("Todo Not found")
        }

        val todo =
            TodoEntity(
                id = id,
                title = request.title,
            )
        todos[index] = todo
    }

    override fun deleteTodo(id: Int) {
        val index = todos.indexOfFirst { it.id == id }

        if (index == -1) {
            throw TodoNotFoundException("Todo Not found")
        }

        todos.removeAt(index)
    }
}
