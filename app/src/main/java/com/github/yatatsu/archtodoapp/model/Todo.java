package com.github.yatatsu.archtodoapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import org.threeten.bp.LocalDateTime;

@Entity public final class Todo {

  @PrimaryKey(autoGenerate = true)
  private int id;

  private String name;

  private String body;

  private LocalDateTime date;

  private TodoStatus status;

  public Todo(int id, String name, String body, LocalDateTime date, TodoStatus status) {
    this.id = id;
    this.name = name;
    this.body = body;
    this.date = date;
    this.status = status;
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

  public TodoStatus getStatus() {
    return status;
  }

  @Override public int hashCode() {
    return id;
  }

  @Override public boolean equals(Object obj) {
    return obj == this || (obj instanceof Todo && ((Todo) obj).id == id);
  }

  @Override public String toString() {
    return "Todo{id='" + id + "', "
        + "name='" + name + "', "
        + "body='" + body + "', "
        + "date='" + date + "', "
        + "status='" + status + "'"
        + "}";
  }
}
