package nhn.calendarapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import nhn.calendarapp.R
import nhn.calendarapp.data.Task

/**
 * Created by nguyennguyen on 1/6/17.
 */
class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    private var taskList: List<Task>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = View.inflate(parent.context, R.layout.item_task, null)
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList!![position]
        holder.tvTaskDesc.text = task.taskDesc
        holder.tvTaskName.text = task.taskName
        holder.tvTime.text = task.taskTime
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

//        var timelineView: TimelineView
        var tvTime: TextView
        var tvTaskName: TextView
        var tvTaskDesc: TextView
//        var tvTimeMark: TextView

        init {
           // timelineView = itemView.findViewById(R.id.timeline_marker) as TimelineView
            //timelineView.initLine(viewType)
            tvTime = itemView.findViewById(R.id.task_time) as TextView
            tvTaskName = itemView.findViewById(R.id.task_name) as TextView
            tvTaskDesc = itemView.findViewById(R.id.task_desc) as TextView
//            tvTimeMark = itemView.findViewById(R.id.tv_task_time) as TextView
        }
    }

}
