package com.example.sqliteroomsample.util

import android.content.Context
import android.widget.Toast

object ContextExtension {

    @JvmStatic
    fun Context.showMessage(msg: String) =
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
