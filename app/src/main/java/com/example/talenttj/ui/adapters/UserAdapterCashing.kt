package com.example.talenttj.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.talenttj.R
import com.example.talenttj.data.ProfessionAndSkill
import com.example.talenttj.data.UserDiffUtil
import com.example.talenttj.data.room.entities.User
import com.example.talenttj.databinding.ItemRecyclerviewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// кэширования без пагинации
class UserAdapterCashing(
    val list: List<User> = emptyList(),
    val onItemClick: (Int) -> (Unit)
) : RecyclerView.Adapter<UserAdapterCashing.TalentViewHolder>() {

    inner class TalentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemRecyclerviewUserBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalentViewHolder {
        return TalentViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_recyclerview_user, parent, false)
        )
    }


    override fun onBindViewHolder(holder: TalentViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            CoroutineScope(Dispatchers.IO).launch {
                val glide = Glide.with(holder.itemView.context)
                try {
                    val imageFoto = glide.load(item.Image)
                    withContext(Dispatchers.Main) {
                        imageFoto.into(holder.binding.imageView)
                    }
                } catch (e: Exception) {
                }
                withContext(Dispatchers.Main) {
                    nameTextView.text = "${item.name} ${item.surname}"

                    profession.text =
                        ProfessionAndSkill.getDefaultProfessions()[0].professionName

                    location.text = "${item.city} ${item.country}"

                    if (!item.working_status) {
                        workingStatusColor.strokeColor = ContextCompat.getColor(
                            holder.itemView.context, R.color.design_default_color_error
                        )
                        constraint.background = getDrawable(
                            holder.itemView.context,
                            R.drawable.recycler_item_styleno
                        )
                    } else {
                        workingStatusColor.strokeColor = ContextCompat.getColor(
                            holder.itemView.context, R.color.green1
                        )
                        constraint.background = getDrawable(
                            holder.itemView.context,
                            R.drawable.recycler_item_style
                        )
                    }
                }
            }
            constraint.setOnClickListener {
                constraint.background = getDrawable(holder.itemView.context, R.color.blue1)
                item.userId.let { it1 -> onItemClick(it1) }
            }
        }
    }

    override fun getItemCount(): Int  = list.size


}