package nhn.calendarapp.repository

import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import nhn.calendarapp.AppDatabase
import nhn.calendarapp.data.Task

/**
 * Created by nguyennguyen on 5/6/17.
 */

class TaskRepositoryImpl constructor(internal var appDatabase: AppDatabase) : TaskRepository {

    override fun addTask(task: Task): Completable {
        return Completable.fromAction { appDatabase.taskDao().insertAll(task) }
    }


    override fun getTasks(): LiveData<List<Task>> {
        return appDatabase.taskDao().getAll()
    }

    override fun getTaskByDate(date: String): LiveData<List<Task>> {
        return appDatabase.taskDao().getTaskByDate(date)
    }

}

