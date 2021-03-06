package nhn.calendarapp.ui.create_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.DatePicker
import android.widget.TimePicker
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskViewModel
import nhn.calendarapp.databinding.ActivityCreateTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityCreateTaskBinding

    private lateinit var taskViewModel: TaskViewModel

    val dateSdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    val timeSdf = SimpleDateFormat("hh:mm aaa", Locale.US)

    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        this.supportActionBar!!.title = baseContext.getString(R.string.create_task)
        binding.handler = Handler(this, this)
        binding.task = Task()
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.edDate.setText(dateSdf.format(calendar.time))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        binding.edTime.setText(timeSdf.format(calendar.time))
    }


    inner class Handler constructor(internal var context: Context, internal var activity: CreateTaskActivity) {

        fun onFabCreateClick() {
            var task = Task()
            task.taskName = binding.edTaskName.text.toString()
            task.taskDesc = binding.edSummary.text.toString()
            task.taskDate = (binding.edDate.text.toString())
            task.taskTime = formatTimeTask(binding.edTime.text.toString())
            taskViewModel.createTask(task)
            finish()
        }

        fun onDateClick() {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(context, activity, year, month, day)
            datePickerDialog.show()
        }

        fun onTimeClick() {
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(context, activity, hour, minute, false)
            timePickerDialog.show()
        }

        fun formatDateTask(date: String): Int {
            return dateSdf.parse(date).time.toInt()
        }

        fun formatTimeTask(time: String): Int {
            return timeSdf.parse(time).time.toInt()
        }

        fun showError() {
            if (TextUtils.isEmpty(binding.edTaskName.text.toString())) {
                binding.edTaskName.error = "Please fill in task name"
            } else if (TextUtils.isEmpty(binding.edSummary.text.toString())) {
                binding.edSummary.error = "Please fill in description"
            } else if (TextUtils.isEmpty(binding.edDate.text.toString())) {
                binding.edDate.error = "Please fill in date"
            } else if (TextUtils.isEmpty(binding.edTime.text.toString())) {
                binding.edTime.error = "Please fill in time"
            }
        }

        fun canCreateTask(): Boolean {
            return !TextUtils.isEmpty(binding.edTaskName.text.toString())
                    && !TextUtils.isEmpty(binding.edSummary.text.toString())
                    && !TextUtils.isEmpty(binding.edDate.text.toString())
                    && !TextUtils.isEmpty(binding.edTime.text.toString())
        }


    }


}
