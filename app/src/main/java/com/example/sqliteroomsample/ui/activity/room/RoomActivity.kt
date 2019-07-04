package com.example.sqliteroomsample.ui.activity.room

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.activity_room.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomActivity : AppCompatActivity(), OnClickListener {

    private val viewModel: RoomViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initComponent()
        doObserve()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert_room -> insertGender()
            R.id.btn_get_users -> getUsers()
        }
    }

    private fun initComponent() {
        btn_insert_room?.setOnClickListener(this)

        btn_get_users.setOnClickListener(this)
    }

    private fun doObserve() {
        viewModel.insertUserLiveData.observe(this, Observer {
            showMessage(it)
        })

        viewModel.getUserLiveDate.observe(this, Observer {
            showMessage(it.toString())
        })
    }

    private fun insertGender() {
        val name = edit_name.text.toString()
        val address = edit_address.text.toString()
        val user = User(name = name, address = address)
        viewModel.insertGender(user)
    }

    private fun getUsers() {
        viewModel.getUser()
    }
}
