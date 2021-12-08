package com.example.talenttj.data.room.entities

import androidx.room.*

@Entity(primaryKeys = ["userId", "idSkill"])

data class UserSkill (
    val userId: Int,
    val idSkill: Int
)
