package com.github.yatatsu.archtodoapp.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import com.github.yatatsu.archtodoapp.repository.TodoRepository;
import java.util.List;
import javax.inject.Inject;

public final class TodoListViewModel extends ViewModel {

  private final TodoRepository todoRepository;
  private LiveData<List<Todo>> todoList = new MutableLiveData<>();

  @Inject TodoListViewModel(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
    todoList = todoRepository.getTodos(TodoStatus.INBOX);
  }

  LiveData<List<Todo>> getTodoList() {
    return todoList;
  }
}
