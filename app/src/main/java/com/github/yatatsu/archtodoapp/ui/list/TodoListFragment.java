package com.github.yatatsu.archtodoapp.ui.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.yatatsu.archtodoapp.R;
import com.github.yatatsu.archtodoapp.databinding.FragmentTodolistBinding;
import com.github.yatatsu.archtodoapp.di.Injectable;
import com.github.yatatsu.archtodoapp.ui.add.AddTodoActivity;
import java.util.Collections;
import javax.inject.Inject;
import timber.log.Timber;

public final class TodoListFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

  @Inject ViewModelProvider.Factory viewModelFactory;
  private TodoAdapter adapter;
  private TodoListViewModel viewModel;

  private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
  private FragmentTodolistBinding binding;

  @Override public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodoListViewModel.class);
    viewModel.getTodoList().observe(this, state -> {
      if (state != null) {
        Timber.d("todos => %s", state.getData());
        binding.setIsLoading(state.isLoading());
        if (state.getError() != null) {
          Toast.makeText(getContext(), state.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
        adapter.setTodoList(state.getData() == null ? Collections.emptyList() : state.getData());
        adapter.notifyDataSetChanged();
      }
    });
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todolist, container, false);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    binding.todoList.setLayoutManager(layoutManager);
    binding.swipeRefresh.setOnRefreshListener(() -> {
      // reload
      viewModel.retry();
    });

    // adapter
    adapter = new TodoAdapter(getContext(), Collections.emptyList());
    binding.todoList.setAdapter(adapter);

    // fab
    binding.fabAddTodo.setOnClickListener(v -> startActivity(new Intent(getContext(),
        AddTodoActivity.class)));

    return binding.getRoot();
  }
}
