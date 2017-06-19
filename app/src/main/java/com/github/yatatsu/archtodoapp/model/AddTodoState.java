package com.github.yatatsu.archtodoapp.model;

public interface AddTodoState {

  final class None implements AddTodoState {}

  final class Loading implements AddTodoState {}

  final class Error implements AddTodoState {
    final Throwable throwable;

    public Error(Throwable throwable) {
      this.throwable = throwable;
    }

    public Throwable getThrowable() {
      return throwable;
    }
  }

  final class Complete implements AddTodoState {}
}
