package com.example.sqliteroomsample.ui.activity.room

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.ui.adapter.UserAdapter
import com.example.sqliteroomsample.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.activity_room.btn_get_users
import kotlinx.android.synthetic.main.activity_room.btn_insert_room
import kotlinx.android.synthetic.main.activity_room.edit_address
import kotlinx.android.synthetic.main.activity_room.edit_name
import kotlinx.android.synthetic.main.activity_room.recycler_users
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomActivity : AppCompatActivity(), OnClickListener {

    private val viewModel: RoomViewModel by viewModel()

    private val userAdapter by lazy { UserAdapter(ArrayList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initComponent()
        doObserve()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert_room -> insertUser()
            R.id.btn_get_users -> getUsers()
        }
    }

    private fun initComponent() {
        btn_insert_room?.setOnClickListener(this)
        btn_get_users.setOnClickListener(this)

        recycler_users?.apply {
            layoutManager = LinearLayoutManager(this@RoomActivity)
            adapter = userAdapter
        }
    }

    private fun doObserve() {
        viewModel.insertUserLiveData.observe(this, Observer {
            showMessage(it)
        })

        viewModel.getUserLiveDate.observe(this, Observer { users ->
            updateUsers(users)
        })
    }

    private fun insertUser() {
        val name = edit_name.text.toString()
        val address = edit_address.text.toString()

        if (!name.isEmpty() && !address.isEmpty()) {
            val user = User(name = name, address = address)
            viewModel.insertUser(user)
        }
    }

    private fun getUsers() {
        viewModel.getUser()
    }

    private fun updateUsers(users: List<User>) {
        showMessage(users.size.toString())
        userAdapter.updateUsers(users)
    }
}
