package nhn.calendarapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import nhn.calendarapp.local.dao.TaskDao
import nhn.calendarapp.model.Task

/**
 * Created by nguyennguyen on 31/5/17.
 */
@Database(entities = arrayOf(Task::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
