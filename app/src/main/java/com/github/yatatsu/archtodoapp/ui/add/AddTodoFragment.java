package com.github.yatatsu.archtodoapp.ui.add;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.yatatsu.archtodoapp.R;
import com.github.yatatsu.archtodoapp.databinding.FragmentAddtodoBinding;
import com.github.yatatsu.archtodoapp.di.Injectable;
import javax.inject.Inject;

public class AddTodoFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

  @Inject ViewModelProvider.Factory viewModelFactory;
  private AddTodoViewModel viewModel;

  private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

  @Override public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTodoViewModel.class);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    FragmentAddtodoBinding binding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_addtodo, container, false);

    return binding.getRoot();
  }
}
