package nhn.calendarapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.databinding.ObservableBoolean
import android.text.TextUtils
import java.util.*

/**
 * Created by nguyennguyen on 30/5/17.
 */

@Entity(tableName = "task")
class Task {

    @Ignore
    val canCreateTask = ObservableBoolean()

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "task_name")
    var taskName: String = ""
        set(value) {
            field = value
            canCreateTask.set(allowCreateTask())
        }


    @ColumnInfo(name = "task_desc")
    var taskDesc: String = ""
        set(value) {
            field = value
            canCreateTask.set(allowCreateTask())
        }

    @ColumnInfo(name = "task_time")
    var taskTime: Int = Calendar.getInstance().timeInMillis.toInt()
        set(value) {
            field = value
            canCreateTask.set(allowCreateTask())
        }

    @ColumnInfo(name = "task_date")
    var taskDate: Int = Calendar.getInstance().timeInMillis.toInt()
        set(value) {
            field = value
            canCreateTask.set(allowCreateTask())
        }


    fun allowCreateTask(): Boolean {
        return !TextUtils.isEmpty(taskName) && !TextUtils.isEmpty(taskDesc)
    }

}
