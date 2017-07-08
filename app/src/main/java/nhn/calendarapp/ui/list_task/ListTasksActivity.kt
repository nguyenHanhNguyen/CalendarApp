package nhn.calendarapp.ui.list_task

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nhn.calendarapp.R
import nhn.calendarapp.databinding.ActivityListTasksBinding

class ListTasksActivity : android.support.v7.app.AppCompatActivity() {

    private lateinit var binding: nhn.calendarapp.databinding.ActivityListTasksBinding

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = android.databinding.DataBindingUtil.setContentView(this, nhn.calendarapp.R.layout.activity_list_tasks)
        this.supportActionBar!!.title = baseContext.getString(nhn.calendarapp.R.string.task_list)
    }

}
