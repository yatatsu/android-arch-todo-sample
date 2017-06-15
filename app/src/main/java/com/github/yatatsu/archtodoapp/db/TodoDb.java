package com.github.yatatsu.archtodoapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.github.yatatsu.archtodoapp.model.Todo;

@Database(entities = { Todo.class }, version = 1, exportSchema = false)
@TypeConverters({ LocalDateTimeConverter.class, TodoStatusConverter.class })
public abstract class TodoDb extends RoomDatabase {

  public abstract TodoDao todoDao();
}
