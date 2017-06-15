package com.github.yatatsu.archtodoapp.repository;

import android.arch.lifecycle.LiveData;
import com.github.yatatsu.archtodoapp.db.TodoDb;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import io.reactivex.Completable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public final class TodoRepository {

  private final TodoDb todoDb;

  @Inject TodoRepository(TodoDb todoDb) {
    this.todoDb = todoDb;
  }

  public LiveData<List<Todo>> getTodos(TodoStatus status) {
    return todoDb.todoDao().findByStatus(status);
  }

  public Completable addTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().add(todo));
  }

  public Completable deleteTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().delete(todo));
  }

  public Completable updateTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().update(todo));
  }
}
