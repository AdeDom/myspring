package com.adedom.myspring.controller

import com.adedom.myspring.data.model.BaseResponse
import com.adedom.myspring.data.model.todo.TodoRequest
import com.adedom.myspring.data.model.todo.TodoResponse
import com.adedom.myspring.data.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo")
class TodoController(
    private val todoRepository: TodoRepository,
) {
    @GetMapping
    fun getTodos(): BaseResponse<List<TodoResponse>> =
        BaseResponse(
            data = todoRepository.getTodos(),
        )

    @GetMapping("/{id}")
    fun getTodo(
        @PathVariable id: Int,
    ): BaseResponse<TodoResponse> =
        BaseResponse(
            data = todoRepository.getTodoById(id),
        )

    @GetMapping("/search")
    fun getSearch(
        @RequestParam title: String,
    ): BaseResponse<TodoResponse> =
        BaseResponse(
            data = todoRepository.getTodoByTitle(title),
        )

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun addTodo(
        @RequestBody request: TodoRequest,
    ): BaseResponse<Unit> =
        BaseResponse(
            data = todoRepository.addTodo(request),
        )

    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Int,
        @RequestBody request: TodoRequest,
    ): BaseResponse<Unit> =
        BaseResponse(
            data = todoRepository.updateTodo(id, request),
        )

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteTodo(
        @PathVariable id: Int,
    ): BaseResponse<Unit> =
        BaseResponse(
            data = todoRepository.deleteTodo(id),
        )
}
