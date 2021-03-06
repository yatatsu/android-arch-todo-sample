package com.github.yatatsu.archtodoapp.di;

import android.arch.lifecycle.ViewModelProvider;
import com.github.yatatsu.archtodoapp.ui.add.AddTodoActivity;
import com.github.yatatsu.archtodoapp.ui.add.AddTodoModule;
import com.github.yatatsu.archtodoapp.ui.list.TodoListActivity;
import com.github.yatatsu.archtodoapp.ui.list.TodoListModule;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * module for activity injection
 */
@Module public abstract class ActivityModule {

  @ContributesAndroidInjector(modules = { TodoListModule.class })
  abstract TodoListActivity contributeTodoListActivity();

  @ContributesAndroidInjector(modules = { AddTodoModule.class })
  abstract AddTodoActivity contributeAddTodoActivity();

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(InjectionViewModelFactory factory);
}
