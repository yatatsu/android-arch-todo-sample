package com.github.yatatsu.archtodoapp.ui.add;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import com.github.yatatsu.archtodoapp.repository.TodoRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import org.threeten.bp.LocalDateTime;
import timber.log.Timber;

public final class AddTodoViewModel extends ViewModel {

  private final TodoRepository todoRepository;
  private final CompositeDisposable disposables = new CompositeDisposable();
  private final MutableLiveData<AddTodoState> addTodoState = new MutableLiveData<>();

  @Inject AddTodoViewModel(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
    addTodoState.setValue(new AddTodoState.None());
  }

  @Override protected void onCleared() {
    disposables.clear();
    addTodoState.setValue(new AddTodoState.None());
  }

  MutableLiveData<AddTodoState> getAddTodoState() {
    return addTodoState;
  }

  void addTodo(String todoName, String todoBody) {
    Timber.d("add Todo");
    Todo todo = new Todo(0, todoName, todoBody, LocalDateTime.now(), TodoStatus.INBOX);
    Disposable disposable = todoRepository.addTodo(todo)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toSingle(AddTodoState.Complete::new)
        .toObservable()
        .cast(AddTodoState.class)
        .onErrorReturn(AddTodoState.Error::new)
        .startWith(new AddTodoState.Loading())
        .doOnNext(state -> Timber.d("state -> %s", state))
        .subscribe(addTodoState::setValue);
    disposables.add(disposable);
  }

  interface AddTodoState {

    final class None implements AddTodoState {}

    final class Loading implements AddTodoState {}

    final class Error implements AddTodoState {
      final Throwable throwable;

      Error(Throwable throwable) {
        this.throwable = throwable;
      }
    }

    final class Complete implements AddTodoState {}
  }
}
