package nhn.calendarapp.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by nguyennguyen on 31/5/17.
 */

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Insert
    fun insertAll(vararg task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task WHERE task_date LIKE :p0")
    fun getTaskDate(date: String): LiveData<List<Task>>
}
