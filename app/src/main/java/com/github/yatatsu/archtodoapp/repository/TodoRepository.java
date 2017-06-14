package com.github.yatatsu.archtodoapp.repository;

import android.arch.lifecycle.LiveData;
import com.github.yatatsu.archtodoapp.model.Todo;
import java.util.List;

public interface TodoRepository {

  LiveData<List<Todo>> getTodos();

  // TODO add/delete
}
