package com.example.sqliteroomsample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteroomsample.R
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.util.autoNotify
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(
    private var users: MutableList<User>,
    @NonNull private val deleteOnClick: (user: User) -> Unit,
    @NonNull private val updateOnClick: (user: User) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    fun upgradeUsers(users: MutableList<User>) = when {
        this.users.isNullOrEmpty() -> {
            this.users = users
            notifyItemRangeInserted(0, users.size)
        }
        else -> {
            autoNotify(this.users, users) { o, n -> o.id == n.id }
        }
    }

    fun setupUsers(users: MutableList<User>) {
        this.users = users
        notifyItemChanged(users.size - 1)
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
        users[position].let { holder.onBind(it, holder.adapterPosition) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User, position: Int) {
            with(itemView) {
                text_user_id.text = user.id.toString()
                text_user_name.text = user.name
                text_user_address.text = user.address

                img_delete_user.setOnClickListener {
                    deleteOnClick(user)
                    removeAt(position)
                }
            }
        }

        private fun removeAt(position: Int) {
            this@UserAdapter.users.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
