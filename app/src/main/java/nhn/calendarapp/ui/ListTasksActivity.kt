package nhn.calendarapp.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nhn.calendarapp.R
import nhn.calendarapp.databinding.ActivityListTasksBinding

class ListTasksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_tasks)
        this.supportActionBar!!.title = baseContext.getString(R.string.task_list)
    }

}
