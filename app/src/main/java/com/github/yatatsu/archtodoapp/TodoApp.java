package com.github.yatatsu.archtodoapp;

import android.app.Application;
import android.content.Context;
import com.github.yatatsu.archtodoapp.di.AppComponent;
import com.github.yatatsu.archtodoapp.di.AppModule;
import com.github.yatatsu.archtodoapp.di.DaggerAppComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;
import timber.log.Timber;

public class TodoApp extends Application {

  private final AppComponent appComponent = createComponent();

  @Override public void onCreate() {
    super.onCreate();

    AndroidThreeTen.init(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  protected AppComponent createComponent() {
    return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  public static TodoApp get(Context context) {
    return (TodoApp) context.getApplicationContext();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
