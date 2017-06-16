package com.github.yatatsu.archtodoapp.ui.add;

import android.arch.lifecycle.ViewModel;
import com.github.yatatsu.archtodoapp.di.ViewModelMapKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module public abstract class AddTodoModule {

  @Binds
  @IntoMap
  @ViewModelMapKey(AddTodoViewModel.class)
  abstract ViewModel bindAddTodoViewModel(AddTodoViewModel addTodoViewModel);

  @ContributesAndroidInjector
  abstract AddTodoFragment contributeAddTodoFragment();
}
