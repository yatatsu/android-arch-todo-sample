package com.github.yatatsu.archtodoapp.ui.add;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.github.yatatsu.archtodoapp.R;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public final class AddTodoActivity extends AppCompatActivity implements LifecycleRegistryOwner,
    HasSupportFragmentInjector {

  @Inject DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
  private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

  @Override public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addtodo);
  }
}
