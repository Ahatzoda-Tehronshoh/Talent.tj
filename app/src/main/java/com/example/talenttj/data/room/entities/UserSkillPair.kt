package com.example.talenttj.data.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserSkillPair (
    @Embedded
    var user: User,
    @Relation(
        parentColumn = "userId",
        entity = Skill::class,
        entityColumn = "skillId",
        associateBy = Junction(
            value = UserSkill::class,
            parentColumn = "userId",
            entityColumn = "idSkill"
        )
    )
    var skill: List<Skill>
)