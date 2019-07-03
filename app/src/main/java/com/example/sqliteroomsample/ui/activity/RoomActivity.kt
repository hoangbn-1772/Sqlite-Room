package com.example.sqliteroomsample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.local.database.UserRoomDatabase

class RoomActivity : AppCompatActivity() {

    private val database by lazy { UserRoomDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }
}
