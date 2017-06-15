package com.github.yatatsu.archtodoapp.db;

import android.arch.persistence.room.TypeConverter;
import com.github.yatatsu.archtodoapp.model.TodoStatus;

public final class TodoStatusConverter {

  @TypeConverter public static TodoStatus toStatus(int id) {
    return TodoStatus.values()[id];
  }

  @TypeConverter public static int toStatus(TodoStatus status) {
    return status.ordinal();
  }
}
