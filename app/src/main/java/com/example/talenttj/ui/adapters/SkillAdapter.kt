package com.example.talenttj.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.talenttj.R
import com.example.talenttj.databinding.ItemRecyclerviewSkillBinding

class SkillAdapter(private val list: List<String>): RecyclerView.Adapter<SkillAdapter.ViewHolderSkill>() {

    class ViewHolderSkill(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemRecyclerviewSkillBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSkill =
        ViewHolderSkill(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_recyclerview_skill, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolderSkill, position: Int) {
        holder.binding.skillName.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}

