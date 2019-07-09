package com.example.sqliteroomsample.ui.activity.room

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.ui.adapter.UserAdapter
import com.example.sqliteroomsample.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.activity_room.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.*

class RoomActivity : AppCompatActivity(), OnClickListener {

    private val viewModel: RoomViewModel by viewModel()

    var user: User? = null

    private val userAdapter by lazy {
        UserAdapter(
            viewModel.users,
            { user -> deleteUser(user) },
            { user -> updateUser(user) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initComponent()
        doObserve()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert_room -> insertUser()
            R.id.btn_get_users -> getUsers()
            R.id.btn_get_users_by_time -> getUsersByTime()
        }
    }

    private fun initComponent() {
        btn_insert_room?.setOnClickListener(this)
        btn_get_users.setOnClickListener(this)
        btn_get_users_by_time.setOnClickListener(this)
        setupUsers()
    }

    private fun doObserve() {
        viewModel.insertUserLiveData.observe(this, Observer { rowId ->
            if (rowId != -1L) {
                this.user?.let { viewModel.users.add(it) }
            }
            user = null
        })

        viewModel.getUserLiveDate.observe(this, Observer {
            viewModel.users.removeAll(viewModel.users)
            viewModel.users.addAll(it)
            setupUsers()
        })

        viewModel.deleteLiveData.observe(this, Observer { rowId ->
            showMessage(rowId.toString())
        })

        viewModel.updateUser.observe(this, Observer {
            showMessage(it.toString())
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertUser() {
        val name = edit_name.text.toString()
        val address = edit_address.text.toString()

        if (name.isNotEmpty() && address.isNotEmpty()) {
//            val time = OffsetDateTime.now()
//            val time = DateFormat.getDateTimeInstance() as SimpleDateFormat
//            time.applyPattern(DATE_TIME_FORMAT)
            val time = Date()
            this.user = User(name = name, address = address, joined_date = time)
            this.user?.let { viewModel.insertUser(it) }

            edit_name.text?.clear()
            edit_address.text?.clear()
        }
    }

    private fun updateUser(user: User) {
        when {
            user.name?.isNotEmpty()!! -> user.name = ""
            else -> user.name = "ABC"
        }
        viewModel.updateUser(user)
    }

    private fun deleteUser(user: User) {
        viewModel.deleteUser(user)
    }

    private fun getUsers() {
        viewModel.getUser()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUsersByTime() {
        val time = OffsetDateTime.now()
    }

    private fun setupUsers() {
        recycler_users?.apply {
            layoutManager = LinearLayoutManager(this@RoomActivity)
            adapter = userAdapter
        }
    }

    companion object {
        private const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    }
}
