package nhn.calendarapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        binding.handler = Handler(this, this)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.edDate.setText(sdf.format(myCalendar.time))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val sdf = SimpleDateFormat("hh:mm aaa", Locale.US)
        myCalendar.set(Calendar.HOUR, hourOfDay)
        myCalendar.set(Calendar.MINUTE, minute)
        binding.edTime.setText(sdf.format(myCalendar.time))
    }


    inner class Handler constructor(internal var context: Context, internal var activity: CreateTaskActivity) {

        fun onFabCreateClick() {
            val task = Task()
            task.taskName = binding.edTaskName.text.toString()
            task.taskDesc = binding.edSummary.text.toString()
            task.taskDate = binding.edDate.text.toString()
            task.taskTime = binding.edTime.text.toString()
            taskViewModel.createTask(task)
            finish()
        }

        fun onDateClick() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(context, activity, year, month, day)
            datePickerDialog.show()
        }

        fun onTimeClick() {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(context, activity, hour, minute, false)
            timePickerDialog.show()
        }

    }


}
