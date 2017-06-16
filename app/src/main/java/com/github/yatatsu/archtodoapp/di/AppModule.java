package com.github.yatatsu.archtodoapp.di;

import android.arch.persistence.room.Room;
import com.github.yatatsu.archtodoapp.TodoApp;
import com.github.yatatsu.archtodoapp.db.TodoDb;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module class AppModule {

  @Provides @Singleton TodoDb todoDb(TodoApp app) {
    return Room.databaseBuilder(app, TodoDb.class, "todo").build();
  }
}
