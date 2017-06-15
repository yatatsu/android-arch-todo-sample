package com.github.yatatsu.archtodoapp.repository;

import android.arch.lifecycle.LiveData;
import com.github.yatatsu.archtodoapp.db.TodoDb;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import io.reactivex.Completable;
import java.util.List;
import javax.inject.Inject;

public final class TodoRepository implements TodoRepositoryService {

  private final TodoDb todoDb;

  @Inject public TodoRepository(TodoDb todoDb) {
    this.todoDb = todoDb;
  }

  @Override public LiveData<List<Todo>> getTodos(TodoStatus status) {
    return todoDb.todoDao().findByStatus(status);
  }

  @Override public Completable addTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().add(todo));
  }

  @Override public Completable deleteTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().delete(todo));
  }

  @Override public Completable updateTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().update(todo));
  }
}
