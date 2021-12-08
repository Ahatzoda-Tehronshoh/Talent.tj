package com.example.talenttj.data

import com.example.talenttj.data.room.entities.Profession
import com.example.talenttj.data.room.entities.Skill

class ProfessionAndSkill {
    // класс содержить 2 листи которые добавляються в Room до его создание
    companion object {
        fun getDefaultProfessions(): List<Profession> {
            val list = mutableListOf<Profession>()
            list.add(
                Profession(
                    professionName = "Android Developer"
                )
            )
            list.add(
                Profession(
                    professionName = "Backend Developer"
                )
            )
            list.add(
                Profession(
                    professionName = "Frontend Developer"
                )
            )
            list.add(
                Profession(
                    professionName = "Full-Stack Developer"
                )
            )
            list.add(
                Profession(
                    professionName = "Web Developer"
                )
            )
            list.add(
                Profession(
                    professionName = "Graphic designer"
                )
            )
            return list
        }

        fun getDefaultTechSkill(): List<Skill> {
            val list = mutableListOf<Skill>()
            list.add(
                Skill(
                    1,
                    skillName = "Kotlin"
                )
            )
            list.add(
                Skill(
                    2,
                    skillName = "Java"
                )
            )
            list.add(
                Skill(
                    3,
                    skillName = "PHP"
                )
            )
            list.add(
                Skill(
                    4,
                    skillName = "HTML"
                )
            )
            list.add(
                Skill(
                    5,
                    skillName = "Css"
                )
            )
            list.add(
                Skill(
                    6,
                    skillName = "JavaScript"
                )
            )
            list.add(
                Skill(
                    7,
                    skillName = "React"
                )
            )
            list.add(
                Skill(
                    8,
                    skillName = "TypeScript"
                )
            )
            list.add(
                Skill(
                    9,
                    skillName = "NodeJS"
                )
            )
            list.add(
                Skill(
                    10,
                    skillName = "Phyton"
                )
            )
            list.add(
                Skill(
                    11,
                    skillName = "SQL"
                )
            )
            list.add(
                Skill(
                    12,
                    skillName = "Swift"
                )
            )
            list.add(
                Skill(
                    13,
                    skillName = "Photoshop"
                )
            )
            list.add(
                Skill(
                    14,
                    skillName = "Illustrator"
                )
            )
            list.add(
                Skill(
                    15,
                    skillName = "Figma"
                )
            )
            return list
        }
    }
}