package com.github.yatatsu.archtodoapp.di;

import com.github.yatatsu.archtodoapp.TodoApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    AndroidSupportInjectionModule.class,
    AppModule.class,
    ActivityModule.class
}) public interface AppComponent extends AndroidInjector<TodoApp> {

  @Component.Builder
  interface Builder {
    @BindsInstance Builder application(TodoApp application);
    AppComponent build();
  }

  void inject(TodoApp app);
}
