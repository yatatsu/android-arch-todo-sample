package com.github.yatatsu.archtodoapp.util;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Binding
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
  @NonNull public final T binding;

  public BindingViewHolder(LayoutInflater inflater, ViewGroup parent,
      @LayoutRes int resId) {
    super(inflater.inflate(resId, parent, false));
    binding = DataBindingUtil.bind(itemView);
  }
}
