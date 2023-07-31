package com.example.chiandroidinternship.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chiandroidinternship.R
import com.example.chiandroidinternship.data.entity.User
import com.example.chiandroidinternship.databinding.UserItemBinding

class UserAdapter(
    private var userList: List<User>,
    private val mListener: (Int) -> Unit,
    private val checkBoxListener: (Int, Boolean) -> Unit,
    private val longListener: (Int) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(
        var view: UserItemBinding
    ) :
        RecyclerView.ViewHolder(view.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val data = userList[position]

        viewHolder.view.tvUserName.text =
            context.getString(R.string.user_name, userList[position].name)
        viewHolder.view.tvUserAge.text =
            context.getString(R.string.user_age, userList[position].age)

        viewHolder.view.checkBox.isChecked = data.isStudent

        viewHolder.view.checkBox.setOnCheckedChangeListener { _, isChecked ->
            data.isStudent = isChecked
            checkBoxListener.invoke(position, isChecked)
        }

        viewHolder.view.root.setOnClickListener {
            mListener.invoke(position)
        }

        viewHolder.view.root.setOnLongClickListener {
            longListener.invoke(position)
            true
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = userList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateUsers(newUsers: List<User>) {
        userList = newUsers
        notifyDataSetChanged()

    }
}