package com.example.sqliteroomsample.ui.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.DialogFragment
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.sqlite.Employee
import kotlinx.android.synthetic.main.dialog_insert_employee.*

class InsertEmployeeDialog(@NonNull private val onSaveEmployee: OnSaveEmployee)
    : DialogFragment(), View.OnClickListener {

    override fun onStart() {
        super.onStart()
        setSizeDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_insert_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert_dialog -> saveEmployee()
        }
    }

    private fun setSizeDialog() {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        dialog?.window?.setLayout(
                (RATIO_WIDTH_DIALOG * displayMetrics.widthPixels).toInt(),
                (RATIO_HEIGHT_DIALOG * displayMetrics.heightPixels).toInt()
        )
    }

    private fun initComponents() {
        btn_insert_dialog.setOnClickListener(this)
    }

    private fun saveEmployee() {
        val name = edit_name?.text.toString() ?: ""
        val address = edit_address?.text.toString() ?: ""
        val phone = edit_phone?.text.toString() ?: ""
        val employee = Employee(name = name, address = address, phone = phone)

        onSaveEmployee.onSaveEmployee(employee)
    }

    companion object {
        private const val RATIO_WIDTH_DIALOG = 0.8
        private const val RATIO_HEIGHT_DIALOG = 0.6
    }

    interface OnSaveEmployee {
        fun onSaveEmployee(employee: Employee)
    }
}
