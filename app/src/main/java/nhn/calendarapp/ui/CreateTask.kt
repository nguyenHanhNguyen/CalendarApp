package nhn.calendarapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import nhn.calendarapp.R
import nhn.calendarapp.databinding.ActivityCreateTaskBinding
import nhn.calendarapp.model.Task
import nhn.calendarapp.model.TaskViewModel

class CreateTask : AppCompatActivity() {

    var binding: ActivityCreateTaskBinding? = null

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        binding!!.handler = Handler(this)
        //taskViewModel = TaskViewModel(this.application)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    inner class Handler constructor(var context: Context) {


        fun onFabCreateClick() {
            val task = Task()
            task.taskName = binding!!.edTaskName.text.toString()
            task.taskDesc = binding!!.edSummary.text.toString()
            taskViewModel.createTask(task)
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

    }


}
