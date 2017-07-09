package nhn.calendarapp.ui.edit_task

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskViewModel
import nhn.calendarapp.databinding.ActivityCreateTaskBinding

/**
 * Created by nguyen on 08/07/2017.
 */

class EditTaskFragment : LifecycleFragment() {

    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<ActivityCreateTaskBinding>(inflater, R.layout.activity_create_task, container, false)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        var taskID = activity.intent.extras.getInt("TASK_ID")
        initView(taskID)
    }

    fun initView(taskID: Int) {
        this.taskViewModel.getTaskById(taskID).observe(this, Observer { inflateUI(it!!) })
    }

    fun inflateUI(task: Task) {
        binding.edTaskName.setText(task.taskName)
        binding.edSummary.setText(task.taskDesc)
        binding.edDate.setText(task.taskDate)
    }

}
