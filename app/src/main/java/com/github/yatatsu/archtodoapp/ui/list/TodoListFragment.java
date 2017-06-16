package com.github.yatatsu.archtodoapp.ui.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.yatatsu.archtodoapp.R;
import com.github.yatatsu.archtodoapp.databinding.FragmentTodolistBinding;
import com.github.yatatsu.archtodoapp.di.Injectable;
import java.util.Collections;
import javax.inject.Inject;
import timber.log.Timber;

public class TodoListFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

  @Inject ViewModelProvider.Factory viewModelFactory;
  private TodoAdapter adapter;
  private TodoListViewModel viewModel;

  private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

  @Override public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodoListViewModel.class);
    viewModel.getTodoList().observe(this, todos -> {
      Timber.d("todos => %s", todos);
      adapter.setTodoList(todos == null ? Collections.emptyList() : todos);
      adapter.notifyDataSetChanged();
    });
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {

    FragmentTodolistBinding binding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_todolist, container, false);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    binding.todoList.setLayoutManager(layoutManager);
    final DividerItemDecoration dividerItemDecoration =
        new DividerItemDecoration(getContext(), layoutManager.getOrientation());
    binding.todoList.addItemDecoration(dividerItemDecoration);

    // adapter
    adapter = new TodoAdapter(getContext(), Collections.emptyList());
    binding.todoList.setAdapter(adapter);

    return binding.getRoot();
  }
}
