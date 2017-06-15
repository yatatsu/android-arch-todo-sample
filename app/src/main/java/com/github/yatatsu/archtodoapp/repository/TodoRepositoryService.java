package com.github.yatatsu.archtodoapp.repository;

import android.arch.lifecycle.LiveData;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import io.reactivex.Completable;
import java.util.List;

public interface TodoRepositoryService {

  LiveData<List<Todo>> getTodos(TodoStatus status);

  Completable addTodo(Todo todo);

  Completable deleteTodo(Todo todo);

  Completable updateTodo(Todo todo);
}
