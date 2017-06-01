package nhn.calendarapp.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nhn.calendarapp.R
import nhn.calendarapp.databinding.ActivityListTasksBinding

class ListTasks : AppCompatActivity() {

    var binding: ActivityListTasksBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_tasks)
    }
}
