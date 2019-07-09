package com.example.sqliteroomsample.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.ui.activity.room.RoomActivity
import com.example.sqliteroomsample.ui.activity.sqlite.SQLiteActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_test_sqlite -> move(SQLiteActivity::class.java)
            R.id.btn_test_room -> move(RoomActivity::class.java)
        }
    }

    private fun initComponents() {
        btn_test_sqlite?.setOnClickListener(this)
        btn_test_room?.setOnClickListener(this)

        demoTime()
    }

    private fun move(activity: Class<*>) {
        Intent(this, activity).apply {
            startActivity(this)
        }
    }

    private fun demoTime() {
        val df = DateFormat.getDateTimeInstance() as SimpleDateFormat
        df.applyPattern(DATE_TIME_FORMAT)
        text_Date_Time.text = df.format(Date().time)
    }

    companion object {
        private const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    }
}
