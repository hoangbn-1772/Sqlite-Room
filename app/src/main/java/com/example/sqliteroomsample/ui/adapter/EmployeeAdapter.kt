package com.example.sqliteroomsample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.sqlite.Employee
import kotlinx.android.synthetic.main.item_employee.view.image_delete
import kotlinx.android.synthetic.main.item_employee.view.image_edit
import kotlinx.android.synthetic.main.item_employee.view.text_address
import kotlinx.android.synthetic.main.item_employee.view.text_id
import kotlinx.android.synthetic.main.item_employee.view.text_name
import kotlinx.android.synthetic.main.item_employee.view.text_phone

class EmployeeAdapter(
    private var employees: List<Employee>?,
    @NonNull private val onUpdate: (employee: Employee) -> Unit,
    @NonNull private val onDelete: (employee: Employee) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    fun updateEmployees(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_employee,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (!employees.isNullOrEmpty()) employees?.size!! else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        employees?.get(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(employee: Employee) {
            itemView.run {
                text_id?.text = employee.id.toString()
                text_name?.text = employee.name
                text_address?.text = employee.address
                text_phone?.text = employee.phone

                image_delete.setOnClickListener { onDelete(employee) }
                image_edit.setOnClickListener { onUpdate(employee) }
            }
        }
    }
}
