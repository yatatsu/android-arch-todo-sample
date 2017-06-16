package com.github.yatatsu.archtodoapp.ui.list;

import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.di.ViewModelMapKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 *
 */
@Module public abstract class TodoListModule {

  @Binds
  @IntoMap
  @ViewModelMapKey(TodoListViewModel.class)
  abstract ViewModel bindTodoListViewModel(TodoListViewModel todoListViewModel);

  @ContributesAndroidInjector
  abstract TodoListFragment contributeTodoListFragment();
}
