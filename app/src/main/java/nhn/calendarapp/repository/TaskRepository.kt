package nhn.calendarapp.repository

import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import nhn.calendarapp.data.Task
import java.util.*

/**
 * Created by nguyennguyen on 5/6/17.
 */

interface TaskRepository {

    fun addTask(task: Task): Completable

    fun getTasks(): LiveData<List<Task>>

    fun getTaskDate(date: Date): LiveData<List<Task>>
}