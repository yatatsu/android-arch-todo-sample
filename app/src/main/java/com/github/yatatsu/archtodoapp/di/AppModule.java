package com.github.yatatsu.archtodoapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.github.yatatsu.archtodoapp.TodoApp;
import com.github.yatatsu.archtodoapp.db.TodoDb;
import com.github.yatatsu.archtodoapp.repository.TodoRepository;
import com.github.yatatsu.archtodoapp.repository.TodoRepositoryService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {

  private final TodoApp app;

  public AppModule(TodoApp app) {
    this.app = app;
  }

  @Provides Context applicationContext() {
    return app;
  }

  @Provides @Singleton TodoDb todoDb(Context context) {
    return Room.databaseBuilder(context, TodoDb.class, "todo").build();
  }

  @Provides @Singleton TodoRepositoryService todoRepositoryService(TodoDb todoDb) {
    return new TodoRepository(todoDb);
  }
}
