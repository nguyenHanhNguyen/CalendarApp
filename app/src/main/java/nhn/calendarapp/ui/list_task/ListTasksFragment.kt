package nhn.calendarapp.ui.list_task

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import nhn.calendarapp.data.TaskViewModel
import nhn.calendarapp.databinding.ListTasksFragmentBinding
import nhn.calendarapp.ui.adapter.TaskItemAdapter
import nhn.calendarapp.ui.create_task.CreateTaskActivity
import nhn.calendarapp.ui.edit_task.EditTaskActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nguyennguyen on 6/6/17.
 */

class ListTasksFragment : LifecycleFragment(), TaskItemAdapter.onTaskClickListener {

    private lateinit var binding: ListTasksFragmentBinding

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var taskItemAdapter: TaskItemAdapter

    val sdfMonth = SimpleDateFormat("MMMM", Locale.US)
    val sdfYear = SimpleDateFormat("yyyy", Locale.US)
    val dateSdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<ListTasksFragmentBinding>(inflater, R.layout.list_tasks_fragment, container, false)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        binding.handler = Handler(context)
        initUI()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadAllTask()
        binding.tvMonth.setText(sdfMonth.format(binding.calendar.firstDayOfCurrentMonth))
        binding.tvYear.setText(sdfYear.format(binding.calendar.firstDayOfCurrentMonth))
    }

    fun initUI() {
        binding.calendar.setUseThreeLetterAbbreviation(true)
        binding.calendar.setFirstDayOfWeek(Calendar.MONDAY)
        binding.tvMonth.setText(sdfMonth.format(binding.calendar.firstDayOfCurrentMonth))
        binding.tvYear.setText(sdfYear.format(binding.calendar.firstDayOfCurrentMonth))
        binding.calendar.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                binding.tvMonth.setText(sdfMonth.format(binding.calendar.firstDayOfCurrentMonth))
                binding.tvYear.setText(sdfYear.format(binding.calendar.firstDayOfCurrentMonth))
            }

            override fun onDayClick(dateClicked: Date?) {
                binding.tvMonth.setText(sdfMonth.format(binding.calendar.firstDayOfCurrentMonth))
                binding.tvYear.setText(sdfYear.format(binding.calendar.firstDayOfCurrentMonth))
                if (dateClicked != null) {
                    loadTaskDate(dateSdf.format(dateClicked))
                }
            }

        })
    }

    fun loadAllTask() {
        taskViewModel.getTaskList().observe(this, Observer<List<Task>> {
            it?.let { setUpRcvView(it) }
        })
    }

    fun loadTaskDate(date: String) {
        taskViewModel.getTaskByDate(date).observe(this, Observer<List<Task>> {
            it?.let { setUpRcvView(it) }
        })
    }


    fun setUpRcvView(tasks: List<Task>) {
        if (tasks.isNotEmpty()) {
            val layoutManager = LinearLayoutManager(context)
            binding.rcvTasksList.layoutManager = layoutManager as RecyclerView.LayoutManager?
            taskItemAdapter = TaskItemAdapter(this)
            taskItemAdapter.setItem(tasks)
            binding.rcvTasksList.adapter = taskItemAdapter
            binding.rcvTasksList.visibility = VISIBLE
            binding.tvNoTask.visibility = GONE
        } else {
            binding.rcvTasksList.visibility = GONE
            binding.tvNoTask.visibility = VISIBLE
        }
    }

    override fun onTaskClick(task: Task) {
        val intent = Intent(context, EditTaskActivity::class.java)
        intent.putExtra("TASK_ID", task.id)
        context.startActivity(intent)
    }


    inner class Handler constructor(var context: Context) {

        fun onFabClick() {
            val intent = Intent(context, CreateTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
//            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }
    }

}
