package nhn.calendarapp.ui

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskViewModel
import nhn.calendarapp.databinding.ActivityListTasksBinding
import nhn.calendarapp.ui.adapter.TaskItemAdapter

class ListTasks : LifecycleActivity() {

    private lateinit var binding: ActivityListTasksBinding

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var taskItemAdapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_tasks)
        binding.handler = Handler(this)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        loadTask()
    }

    fun loadTask() {
        taskViewModel.getTaskList().observe(this, Observer<List<Task>> {
            it?.let{setUpRcvView(it)}
        } )

    }

    fun setUpRcvView(tasks: List<Task>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rcvTasksList.layoutManager = layoutManager
        taskItemAdapter = TaskItemAdapter()
        taskItemAdapter.setItem(tasks)
        binding.rcvTasksList.adapter = taskItemAdapter
    }

    inner class Handler constructor(var context: Context) {

        fun onFabClick() {
            intent = Intent(context, CreateTask::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            application.startActivity(intent)
        }
    }

}
