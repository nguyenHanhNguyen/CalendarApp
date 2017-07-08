package nhn.calendarapp.ui.edit_task

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskViewModel
import nhn.calendarapp.databinding.ActivityCreateTaskBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nguyen on 08/07/2017.
 */
class EditTaskActivity : AppCompatActivity() {

    private val taskID = "TASK_ID"
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    val timeSdf = SimpleDateFormat("hh:mm aaa", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        this.supportActionBar!!.title = "Edit Task"
        this.taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        this.initData()
    }

    fun initData() {
        val taskID = intent.extras.getInt(taskID)
        var task: Task
        if (taskID != null) {
            task = this.taskViewModel.getTaskById(taskID)
            binding.edTaskName.setText(task.taskName)
            binding.edSummary.setText(task.taskDesc)
            binding.edDate.setText(task.taskDate)

        }

    }

}