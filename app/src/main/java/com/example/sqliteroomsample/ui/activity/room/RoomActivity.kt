package com.example.sqliteroomsample.ui.activity.room

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.ui.adapter.UserAdapter
import com.example.sqliteroomsample.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.activity_room.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        viewModel.insertUserLiveData.observe(this, Observer { rowId ->
            if (rowId != -1L) {
                this.user?.let { viewModel.users.add(it) }
            }
        })

        viewModel.getUserLiveDate.observe(this, Observer {
            viewModel.users.removeAll(viewModel.users)
            viewModel.users.addAll(it)
        })

        viewModel.deleteLiveData.observe(this, Observer { rowId ->
            showMessage(rowId.toString())
        })
    }

    private fun insertUser() {
        val name = edit_name.text.toString()
        val address = edit_address.text.toString()

        if (name.isNotEmpty() && address.isNotEmpty()) {
            this.user = User(name = name, address = address)
            this.user?.let { viewModel.insertUser(it) }

            edit_name.text?.clear()
            edit_address.text?.clear()
        }
    }

    private fun updateUser(user: User) {

    }

    private fun deleteUser(user: User) {
        viewModel.deleteUser(user)
    }

    private fun getUsers() {
        viewModel.getUser()
    }
}
