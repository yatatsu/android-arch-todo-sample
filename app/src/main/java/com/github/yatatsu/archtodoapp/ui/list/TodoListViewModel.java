package com.github.yatatsu.archtodoapp.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.model.LoadState;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import com.github.yatatsu.archtodoapp.repository.TodoRepository;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public final class TodoListViewModel extends ViewModel {

  private MutableLiveData<TodoStatus> status = new MutableLiveData<>();
  private LiveData<LoadState<List<Todo>>> todoList = new MediatorLiveData<>();

  @Inject TodoListViewModel(TodoRepository todoRepository) {
    todoList = Transformations.switchMap(status, status -> {
      Flowable<LoadState<List<Todo>>> stateFlowable = todoRepository.getTodos(status)
          .map(LoadState::data)
          .onErrorReturn(LoadState::error)
          .startWith(LoadState.loading())
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnNext(s -> Timber.d("new state => %s", s));
      return LiveDataReactiveStreams.fromPublisher(stateFlowable);
    });
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
