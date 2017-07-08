package nhn.calendarapp.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nhn.calendarapp.AppDatabase
import nhn.calendarapp.repository.TaskRepository
import nhn.calendarapp.repository.TaskRepositoryImpl

/**
 * Created by nguyennguyen on 1/6/17.
 */

class TaskViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var taskRepository: TaskRepository
    private var appDatabase: AppDatabase = AppDatabase.createPersistenceDatabase(application.applicationContext)

    init {
        taskRepository = TaskRepositoryImpl(appDatabase)
    }

    fun createTask(task: Task) {
        taskRepository.addTask(task).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {

                    }

                })
    }

    fun getTaskList(): LiveData<List<Task>> {
        return taskRepository.getTasks()
    }

    fun getTaskByDate(date: String): LiveData<List<Task>> {
        return taskRepository.getTaskByDate(date)
    }

}