package com.github.yatatsu.archtodoapp.model;

import android.support.annotation.Nullable;

public class LoadState<T> {

  private boolean loading;

  private T data;

  private Throwable error;

  public LoadState(boolean loading, @Nullable T data, @Nullable Throwable error) {
    this.loading = loading;
    this.data = data;
    this.error = error;
  }

  public boolean isLoading() {
    return loading;
  }

  public T getData() {
    return data;
  }

  public Throwable getError() {
    return error;
  }

  @Override public String toString() {
    return "LoadState{"
        + "loading=" + loading + ", "
        + "data=" + data + ", "
        + "error=" + error + ", "
        + "}";
  }

  @Override public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override public int hashCode() {
    return super.hashCode();
  }

  public static <T> LoadState<T> loading() {
    return new LoadState<>(true, null, null);
  }

  public static <T> LoadState<T> error(Throwable t) {
    return new LoadState<>(false, null, t);
  }

  public static <T> LoadState<T> data(T data) {
    return new LoadState<>(false, data, null);
  }
}
