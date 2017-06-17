package com.github.yatatsu.archtodoapp.ui.add;

import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import com.github.yatatsu.archtodoapp.repository.TodoRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import org.threeten.bp.LocalDateTime;
import timber.log.Timber;

public final class AddTodoViewModel extends ViewModel {

  private final TodoRepository todoRepository;

  private Disposable disposable;

  @Inject AddTodoViewModel(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override protected void onCleared() {
    if (disposable != null) {
      disposable.dispose();
    }
  }

  void addTodo(String todoName, String todoBody) {
    Timber.d("add Todo");
    Todo todo = new Todo(0, todoName, todoBody, LocalDateTime.now(), TodoStatus.INBOX);
    disposable = todoRepository.addTodo(todo)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> Timber.d("saved"),
            t -> Timber.e(t, "save error"));
  }
}
