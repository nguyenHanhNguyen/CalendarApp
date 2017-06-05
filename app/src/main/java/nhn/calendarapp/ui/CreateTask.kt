package nhn.calendarapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskViewModel
import nhn.calendarapp.databinding.ActivityCreateTaskBinding

class CreateTask : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTaskBinding

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        binding.handler = Handler()
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

    }

    inner class Handler {

        fun onFabCreateClick() {
            val task = Task()
            task.taskName = binding.edTaskName.text.toString()
            task.taskDesc = binding.edSummary.text.toString()
            taskViewModel.createTask(task)
            finish()
        }


    }


}
