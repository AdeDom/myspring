package com.adedom.myspring.controller

import com.adedom.myspring.data.model.BaseResponse
import com.adedom.myspring.data.model.todo.TodoRequest
import com.adedom.myspring.data.model.todo.TodoResponse
import com.adedom.myspring.domain.usecase.AddTodoUseCase
import com.adedom.myspring.domain.usecase.DeleteTodoUseCase
import com.adedom.myspring.domain.usecase.GetTodoAllUseCase
import com.adedom.myspring.domain.usecase.GetTodoByIdUseCase
import com.adedom.myspring.domain.usecase.GetTodoByTitleUseCase
import com.adedom.myspring.domain.usecase.UpdateTodoUseCase
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
    private val getTodoAllUseCase: GetTodoAllUseCase,
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val getTodoByTitleUseCase: GetTodoByTitleUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
) {
    @GetMapping
    fun getTodos(): BaseResponse<List<TodoResponse>> =
        BaseResponse(
            data = getTodoAllUseCase.execute(),
        )

    @GetMapping("/{id}")
    fun getTodo(
        @PathVariable id: Int?,
    ): BaseResponse<TodoResponse> =
        BaseResponse(
            data = getTodoByIdUseCase.execute(id),
        )

    @GetMapping("/search")
    fun getSearch(
        @RequestParam title: String?,
    ): BaseResponse<TodoResponse> =
        BaseResponse(
            data = getTodoByTitleUseCase.execute(title),
        )

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun addTodo(
        @RequestBody request: TodoRequest,
    ): BaseResponse<String> {
        addTodoUseCase.execute(request)
        return BaseResponse(
            data = "Add todo success",
        )
    }

    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Int?,
        @RequestBody request: TodoRequest,
    ): BaseResponse<String> {
        updateTodoUseCase.execute(id, request)
        return BaseResponse(
            data = "Update todo success",
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteTodo(
        @PathVariable id: Int?,
    ): BaseResponse<String> {
        deleteTodoUseCase.execute(id)
        return BaseResponse(
            data = "Delete todo success",
        )
    }
}
