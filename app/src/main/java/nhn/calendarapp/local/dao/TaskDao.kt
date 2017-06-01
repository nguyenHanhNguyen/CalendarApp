package nhn.calendarapp.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import nhn.calendarapp.model.Task

/**
 * Created by nguyennguyen on 31/5/17.
 */

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(vararg task: Task)

    @Delete
    fun delete(task: Task)
}