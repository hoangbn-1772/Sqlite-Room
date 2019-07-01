package com.example.sqliteroomsample.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.local.DatabaseHelper
import com.example.sqliteroomsample.data.model.Employee
import com.example.sqliteroomsample.ui.dialog.InsertEmployeeDialog
import kotlinx.android.synthetic.main.activity_sqlite.*


class SQLiteActivity : AppCompatActivity(), View.OnClickListener {

    private val databaseHelper by lazy { DatabaseHelper.getInstance(this) }

    private val insertEmployeeDialog by lazy { InsertEmployeeDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert_data -> insertEmployee()
            R.id.btn_read_data -> readEmployee()
        }
    }

    private fun initComponents() {
        btn_insert_data?.setOnClickListener(this)
        btn_read_data?.setOnClickListener(this)
        btn_update_data?.setOnClickListener(this)
        btn_delete_data?.setOnClickListener(this)
    }

    private fun insertEmployee() {
//        insertEmployeeDialog.show(supportFragmentManager, "")
        val employee = Employee(0, "A", "B", "C")
        databaseHelper.insertEmployee(employee)
    }

    private fun readEmployee(){

    }

    private fun delete() {

    }

    private fun edit() {

    }

    companion object {

    }
}
