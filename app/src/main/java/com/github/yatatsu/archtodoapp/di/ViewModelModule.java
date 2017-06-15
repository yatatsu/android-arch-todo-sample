package com.github.yatatsu.archtodoapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.github.yatatsu.archtodoapp.ui.todolist.TodoListViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelMapKey(TodoListViewModel.class)
  abstract ViewModel bindTodoListViewModel(TodoListViewModel todoListViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(InjectionViewModelFactory factory);
}
