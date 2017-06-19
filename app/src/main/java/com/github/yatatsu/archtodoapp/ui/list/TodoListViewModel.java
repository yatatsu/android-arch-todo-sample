package com.github.yatatsu.archtodoapp.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.model.LoadState;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import com.github.yatatsu.archtodoapp.repository.TodoRepository;
import java.util.List;
import javax.inject.Inject;

public final class TodoListViewModel extends ViewModel {

  private MutableLiveData<TodoStatus> status = new MutableLiveData<>();
  private LiveData<LoadState<List<Todo>>> todoList = new MediatorLiveData<>();

  @Inject TodoListViewModel(TodoRepository todoRepository) {
    todoList = Transformations.switchMap(status, todoRepository::getTodos);
    status.setValue(TodoStatus.INBOX);
  }

  void retry() {
    // set same value
    status.setValue(status.getValue());
  }

  LiveData<LoadState<List<Todo>>> getTodoList() {
    return todoList;
  }
}
