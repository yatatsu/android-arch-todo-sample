package com.github.yatatsu.archtodoapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.model.TodoStatus;
import java.util.List;

@Dao public interface TodoDao {

  @Query("SELECT * FROM todo WHERE status = :status")
  LiveData<List<Todo>> findByStatus(TodoStatus status);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void add(Todo todo);

  @Delete
  void delete(Todo todo);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(Todo todo);
}
