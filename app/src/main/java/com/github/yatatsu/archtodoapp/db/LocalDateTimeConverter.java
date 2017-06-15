package com.github.yatatsu.archtodoapp.db;

import android.arch.persistence.room.TypeConverter;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

public final class LocalDateTimeConverter {

  @TypeConverter public static LocalDateTime toDate(Long timestamp) {
    return timestamp == null
        ? null
        : LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofTotalSeconds(0));
  }

  @TypeConverter public static Long toTimestamp(LocalDateTime date) {
    return date == null
        ? null
        : date.toInstant(ZoneOffset.ofTotalSeconds(0)).getEpochSecond();
  }
}
