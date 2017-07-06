package nhn.calendarapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskDao

/**
 * Created by nguyennguyen on 31/5/17.
 */
@Database(entities = arrayOf(Task::class), version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DB_NAME = "task.db"

        fun createPersistenceDatabase(context: Context) : AppDatabase
            = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME ).build()
    }


}
