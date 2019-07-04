package com.example.sqliteroomsample.ui.activity.sqlite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.local.database.DatabaseHelper
import com.example.sqliteroomsample.data.model.sqlite.Employee
import com.example.sqliteroomsample.ui.adapter.EmployeeAdapter
import com.example.sqliteroomsample.ui.dialog.InsertEmployeeDialog
import com.example.sqliteroomsample.util.ContextExtension.showMessage
import kotlinx.android.synthetic.main.activity_sqlite.*


class SQLiteActivity : AppCompatActivity(), View.OnClickListener, InsertEmployeeDialog.OnSaveEmployee {

    private val databaseHelper by lazy { DatabaseHelper.getInstance(this) }

    private val insertEmployeeDialog by lazy { InsertEmployeeDialog(this) }

    private val employeeAdapter by lazy { EmployeeAdapter(null) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert_data -> insertEmployee()
            R.id.btn_read_all_data -> getAllEmployee()
            R.id.btn_read_data_by_id -> getEmployeeById(2)
            R.id.btn_update_data -> updateEmployee()
            R.id.btn_delete_data -> deleteEmployeeByName("hoang")
        }
    }

    override fun onSaveEmployee(employee: Employee) {
        if (employee.name != "" && employee.address != "" && employee.phone != "") {

            val idRow = databaseHelper.insertEmployee(employee)

            when {
                idRow != -1L -> showMessage("Da luu")
                else -> showMessage("Luu that bai")
            }
        }
        insertEmployeeDialog.dismiss()
    }

    private fun initComponents() {
        btn_insert_data?.setOnClickListener(this)
        btn_read_all_data?.setOnClickListener(this)
        btn_read_data_by_id.setOnClickListener(this)
        btn_update_data?.setOnClickListener(this)
        btn_delete_data?.setOnClickListener(this)
    }

    private fun insertEmployee() {
        insertEmployeeDialog.show(supportFragmentManager, "")
    }

    private fun getAllEmployee() {
        val employees = databaseHelper.getAllEmployee()
        if (employees.isNullOrEmpty()) return
        employeeAdapter.updateEmployees(employees)

        recycler_employees?.apply {
            adapter = employeeAdapter
            layoutManager = LinearLayoutManager(this@SQLiteActivity)
        }
    }

    private fun getEmployeeById(employeeId: Int) {
        val employeeName = databaseHelper.getEmployeeNameById(employeeId) ?: return
        showMessage(employeeName)
    }

    private fun delete() {

    }

    private fun updateEmployee() {
        val count = databaseHelper.updateEmployee("HN")
        showMessage(count.toString())
    }

    private fun deleteEmployeeByName(employeeName: String) {
        val count = databaseHelper.deleteEmployee(employeeName)
        showMessage(count.toString())
    }

    companion object {

    }
}
