package nhn.calendarapp.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import java.util.*

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

    @Query("SELECT * FROM TASK WHERE task_date IN (:date)")
    fun getTaskDate(date : Date): LiveData<List<Task>>
}
