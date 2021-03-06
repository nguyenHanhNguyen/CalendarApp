package nhn.calendarapp.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.github.vipulasri.timelineview.TimelineView
import nhn.calendarapp.R
import nhn.calendarapp.data.Task
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nguyennguyen on 1/6/17.
 */
class TaskItemAdapter constructor(var listener: onTaskClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    private var taskList: List<Task>? = null
    private lateinit var context: Context

    interface onTaskClickListener {
        fun onTaskClick(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = View.inflate(parent.context, R.layout.item_task, null)
        context = parent.context
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sdf = SimpleDateFormat("hh:mm aaa", Locale.US)
        val task = taskList!![position]
        holder.tvTaskDesc.text = task.taskDesc
        holder.tvTaskName.text = task.taskName
        holder.tvTime.text = sdf.format(task.taskTime)
        holder.timelineView.setMarker(context.resources.getDrawable(R.drawable.ic_marker))
        holder.container.setOnClickListener { listener.onTaskClick(task) }
    }

    override fun getItemCount(): Int {
        return taskList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun setItem(tasks: List<Task>) {
        taskList = tasks
    }

    inner class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        var timelineView: TimelineView
        var tvTime: TextView
        var tvTaskName: TextView
        var tvTaskDesc: TextView
        var container: LinearLayout

        init {
            timelineView = itemView.findViewById(R.id.timeline_marker) as TimelineView
            timelineView.initLine(viewType)
            tvTime = itemView.findViewById(R.id.task_time) as TextView
            tvTaskName = itemView.findViewById(R.id.task_name) as TextView
            tvTaskDesc = itemView.findViewById(R.id.task_desc) as TextView
            container = itemView.findViewById(R.id.container) as LinearLayout
        }

    }

}
