package com.example.talenttj.data

import androidx.recyclerview.widget.DiffUtil
import com.example.talenttj.data.room.entities.User

class UserDiffUtil : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.userId == newItem.userId

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}