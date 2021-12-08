package com.example.talenttj.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skill(
    @PrimaryKey(autoGenerate = true)
    val skillId: Int,
    val skillName: String
)
