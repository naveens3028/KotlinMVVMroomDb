package com.example.kotlinmvvmexample.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvmexample.Database.Userdata
import com.example.kotlinmvvmexample.Model.User
import com.example.kotlinmvvmexample.R


class UserAdapter(private val userList: List<User>?) : RecyclerView.Adapter<UserAdapter.BaseViewHolder>() {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.user_row_item, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return if (userList != null && userList.size > 0) {
            userList.size
        } else {
            0
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var txtName: TextView
        var txtEmail: TextView
        var txtPhone: TextView
        override fun clear() {
            txtName.text = ""
            txtEmail.text = ""
            txtPhone.text = ""
        }

        override fun onBind(position: Int) {
            super.onBind(position)
            val user = userList!![position]
            if (user.name != null) {
                txtName.text = user.name
            }
            if (user.email != null) {
                txtEmail.text = user.email
            }
            if (user.phone != null) {
                txtPhone.text = user.phone
            }
        }

        init {
            txtName = itemView.findViewById(R.id.txt_name)
            txtEmail = itemView.findViewById(R.id.txt_email)
            txtPhone = itemView.findViewById(R.id.txt_phone)
        }
    }

    abstract inner class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var currentPosition = 0
            private set

        protected abstract fun clear()
        open fun onBind(position: Int) {
            currentPosition = position
            clear()
        }

    }

    companion object {
        private const val TAG = "Adapter"
    }

}