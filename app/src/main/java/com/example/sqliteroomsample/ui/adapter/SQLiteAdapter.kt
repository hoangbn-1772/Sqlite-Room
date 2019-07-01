package com.example.sqliteroomsample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

class SQLiteAdapter(private var employees: List<Employee>)
    : RecyclerView.Adapter<SQLiteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_employee,
                        parent,
                        false)
        )
    }

    override fun getItemCount(): Int {
        return if (employees.isNullOrEmpty()) 1 else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(employees[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(employee: Employee) {
            itemView.run {
                text_id?.text = employee.id.toString()
                text_name?.text = employee.name
                text_address?.text = employee.address
                text_phone?.text = employee.phone
            }
        }
    }
}
