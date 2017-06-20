package com.github.yatatsu.archtodoapp.repository;

import com.github.yatatsu.archtodoapp.db.TodoDb;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public final class TodoRepository {

  private final TodoDb todoDb;
  private int count;

  @Inject TodoRepository(TodoDb todoDb) {
    this.todoDb = todoDb;
  }

  public Flowable<List<Todo>> getTodos(TodoStatus status) {
    return todoDb.todoDao().findByStatus(status)
        .compose(upstream -> {
          // this is error and loading mock!!
          return upstream.delay(2, TimeUnit.SECONDS)
              .map(t -> {
                count++;
                if (count % 2 == 0) {
                  throw new RuntimeException("stub error!!!");
                }
                return t;
              });
        });
  }

  public Completable addTodo(Todo todo) {
    return Completable.fromAction(() -> {
      // this is error and loading mock!!
      Thread.sleep(2000);
      count++;
      if (count % 2 == 0) {
        throw new RuntimeException("stub error!!!");
      }

      todoDb.todoDao().add(todo);
    });
  }

  public Completable deleteTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().delete(todo));
  }

  public Completable updateTodo(Todo todo) {
    return Completable.fromAction(() -> todoDb.todoDao().update(todo));
  }
}
