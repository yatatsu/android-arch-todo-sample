package com.github.yatatsu.archtodoapp.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.github.yatatsu.archtodoapp.R;
import com.github.yatatsu.archtodoapp.databinding.LiTodoItemBinding;
import com.github.yatatsu.archtodoapp.model.Todo;
import com.github.yatatsu.archtodoapp.util.BindingViewHolder;
import java.util.List;

/**
 * Adapter
 */
final class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoListViewHolder> {
  private final LayoutInflater inflater;
  private List<Todo> todoList;

  TodoAdapter(Context context, @NonNull List<Todo> todos) {
    this.inflater = LayoutInflater.from(context);
    this.todoList = todos;
  }

  @Override public TodoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TodoListViewHolder(inflater, parent);
  }

  @Override public void onBindViewHolder(TodoListViewHolder holder, int position) {
    Todo todo = todoList.get(position);
    holder.binding.setTodo(todo);
    holder.binding.executePendingBindings();
  }

  void setTodoList(@NonNull List<Todo> todoList) {
    this.todoList = todoList;
  }

  @Override public int getItemCount() {
    return todoList.size();
  }

  static class TodoListViewHolder extends BindingViewHolder<LiTodoItemBinding> {

    TodoListViewHolder(LayoutInflater inflater, ViewGroup parent) {
      super(inflater, parent, R.layout.li_todo_item);
    }
  }
}
