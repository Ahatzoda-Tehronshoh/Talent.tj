package com.example.talenttj.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profession")
data class Profession (
        @PrimaryKey(autoGenerate = true)
        val professionId: Int = 0,

        val professionName: String
        )