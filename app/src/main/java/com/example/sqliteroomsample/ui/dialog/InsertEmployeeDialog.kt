package com.example.sqliteroomsample.ui.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.sqliteroomsample.R

class InsertEmployeeDialog : DialogFragment() {

    override fun onStart() {
        super.onStart()
        setSizeDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_insert_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setSizeDialog() {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        dialog?.window?.setLayout(
                (RATIO_WIDTH_DIALOG * displayMetrics.widthPixels).toInt(),
                (RATIO_HEIGHT_DIALOG * displayMetrics.heightPixels).toInt()
        )
    }

    companion object {
        private const val RATIO_WIDTH_DIALOG = 0.8
        private const val RATIO_HEIGHT_DIALOG = 0.6
    }
}
