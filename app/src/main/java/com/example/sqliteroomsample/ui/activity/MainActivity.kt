package com.example.sqliteroomsample.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sqliteroomsample.R
import kotlinx.android.synthetic.main.activity_main.*

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
    }

    private fun move(activity: Class<*>) {
        Intent(this, activity).apply {
            startActivity(this)
        }
    }
}
