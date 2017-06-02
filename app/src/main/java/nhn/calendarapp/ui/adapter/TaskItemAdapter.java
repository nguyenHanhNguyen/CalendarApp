package nhn.calendarapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;

import nhn.calendarapp.R;
import nhn.calendarapp.model.Task;

/**
 * Created by nguyennguyen on 1/6/17.
 */

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {

    private ArrayList<Task> taskList;

    public TaskItemAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_task, null);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.tvTaskDesc.setText(task.getTaskDesc());
        holder.tvTaskName.setText(task.getTaskName());
        holder.tvTime.setText(task.getTaskTime());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TimelineView timelineView;
        TextView tvTime, tvTaskName, tvTaskDesc;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            timelineView = (TimelineView) itemView.findViewById(R.id.timeline_marker);
            timelineView.initLine(viewType);
            tvTime = (TextView) itemView.findViewById(R.id.tv_task_time);
            tvTaskName = (TextView) itemView.findViewById(R.id.task_name);
            tvTaskDesc = (TextView) itemView.findViewById(R.id.task_desc);
        }
    }

}
