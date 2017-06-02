package nhn.calendarapp.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import nhn.calendarapp.AppDatabase

/**
 * Created by nguyennguyen on 1/6/17.
 */

class TaskViewModel constructor(application: Application): AndroidViewModel(application) {

    private var tasksList: LiveData<ArrayList<Task>>? = null
    private var database: AppDatabase? = null

    init {
        database = AppDatabase.createPersistenceDatabase(application)
    }

    fun TaskViewModel(application: Application) {
        database = AppDatabase.createPersistenceDatabase(application)
    }

    fun createTask(task: Task) = database!!.taskDao().insertAll(task)

    fun getTaskList() = database!!.taskDao().getAll()

}