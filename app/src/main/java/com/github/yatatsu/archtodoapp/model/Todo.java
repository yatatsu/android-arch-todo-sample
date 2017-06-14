package com.github.yatatsu.archtodoapp.model;

import android.arch.persistence.room.PrimaryKey;
import org.threeten.bp.LocalDateTime;

public class Todo {

  @PrimaryKey(autoGenerate = true)
  private int id;

  private String name;

  private String body;

  private LocalDateTime date;

  public Todo(int id, String name, String body, LocalDateTime date) {
    this.id = id;
    this.name = name;
    this.body = body;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBody() {
    return body;
  }

  public LocalDateTime getDate() {
    return date;
  }

  @Override public int hashCode() {
    return id;
  }

  @Override public boolean equals(Object obj) {
    return obj == this || (obj instanceof Todo && ((Todo) obj).id == id);
  }
}
