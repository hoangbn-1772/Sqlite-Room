package com.example.sqliteroomsample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.util.autoNotify
import kotlinx.android.synthetic.main.item_user.view.text_user_address
import kotlinx.android.synthetic.main.item_user.view.text_user_id
import kotlinx.android.synthetic.main.item_user.view.text_user_name

class UserAdapter(private var users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    fun updateUsers(users: List<User>) = when {
        this.users.isNullOrEmpty() -> {
            this.users = users
            notifyItemRangeInserted(0, users.size)
        }
        else -> {
            autoNotify(this.users, users) { o, n -> o.id == n.id }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (!users.isNullOrEmpty()) users.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        users.get(position).let { holder.onBind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User) {
            with(itemView) {
                text_user_id.text = user.id.toString()
                text_user_name.text = user.name
                text_user_address.text = user.address
            }
        }
    }
}
