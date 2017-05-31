package nhn.calendarapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by nguyennguyen on 30/5/17.
 */

@Entity(tableName = "task")
class Task {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "task_name")
    var taskName: String = ""

    @ColumnInfo(name = "task_desc")
    var taskDesc: String = ""

    @ColumnInfo(name = "task_time")
    var taskTime: String = ""

    @ColumnInfo(name = "task_date")
    var taskDate: String = ""

}
