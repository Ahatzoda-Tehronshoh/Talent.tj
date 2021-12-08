package com.example.talenttj.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.talenttj.R
import com.example.talenttj.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var controller: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("Error!!!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController

        binding.bottomNavigationView.setupWithNavController(controller)
    }
    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}





/*coroutine.launch {
    for (skill in listOf<Int>(10, 15))
        dao.addUserSkill(
            UserSkill(
                1,
                skill
            )
        )
}*/
/*
       var user = User(
            userId = 1,
            name = "Tehronshoh",
            surname = "Karimov",
            email = "tehronkarimov55@mail.ru",
            city = "Душанбе",
            country = "Таджикистан",
            birthDay = "12.05.2021",
            education = "Я студент 2-го курса направлении Прикладная математика и Информатика Филиал Московского Государственного Университета имени М.В.Ломоносов ",
            professionID = 1,
            working_status = true,
            userDesc = "",
            phone = "+992 92 746 11 10",
            gitHub = "",
            linkedIn = "",
            faceBook = "",
            Image = "https://images.hdqwalls.com/download/mountain-lake-beautiful-night-3840x2400.jpg"
        )
        user.skills = listOf(1, 2, 6, 15)
        userCounter += 1

        coroutine.launch {
            dao.addUser(user)

            for (skill in user.skills)
                dao.addUserSkill(
                  UserSkill(
                        1,
                        skill
                    )
                )
        }

    val user1 = User(
            userId = 2,
            name = "Bill",
            surname = "Gates",
            email = "billGates99@mail.ru",
            city = "Худжанд",
            country = "Америка",
            birthDay = "12.05.1987",
            education = "Я студент 2-го курса направлениb",
            professionID = 2,
            working_status = false,
            userDesc = "",
            phone = "+992 92 746 11 10",
            gitHub = "",
            linkedIn = "",
            faceBook = "",
            Image = "https://startupreviews.ru/wp-content/uploads/2017/10/bill-gates-vr-education.jpg",
        )
        user1.skills = listOf(10, 15)
        userCounter += 1

        coroutine.launch {
            dao.addUser(user1)

            for (skill in user1.skills)
                dao.addUserSkill(
                    UserSkill(
                        2,
                        skill
                    )
                )
        }

        val user2 = User(
            userId = 3,
            name = "Islom",
            surname = "Din",
            email = "islomDin@mail.ru",
            city = "Москва",
            country = "Россия",
            birthDay = "12.05.2000",
            education = "Я студент 2-го курса направлении Прикладная математика и Информатика Филиал Московского Государственного Университета имени М.В.Ломоносов  Я студент 2-го курса направлении Прикладная математика и Информатика Филиал Московского Государственного Университета имени М.В.Ломоносов Я студент 2-го курса направлении Прикладная математика и Информатика Филиал Московского Государственного Университета имени М.В.Ломоносов ",
            professionID = 3,
            working_status = true,
            userDesc = "Я студент 2-го курса направлении Прикладная математика и Информатика Филиал Московского Государственного Университета имени М.В.Ломоносов ",
            phone = "+992 92 746 11 10",
            gitHub = "",
            linkedIn = "",
            faceBook = "",
            Image = "https://cdn.wallpapersafari.com/51/97/sGSp7H.jpg"
        )
        user2.skills = listOf(1, 2, 6)
        userCounter += 1

        coroutine.launch {
            dao.addUser(user2)

            for (skill in user2.skills)
                dao.addUserSkill(
                    UserSkill(
                        3,
                        skill
                    )
                )
        }*/

/*val talent = Talent(
    id_talent = 1,
    name = "Tehron",
    surname = "Ahatzoda",
    email = "",
    city = "",
    country = "",
    birthDay = "12.05.2021",
    education = "",
    profession_id = 1,
    working_status = true,
    about_talent = "",
    phone = "+992 92 746 11 10",
    gitHub = "",
    linkedIn = "",
    faceBook = "",
    Image = 2,
)
talent.techSkills = listOf("Kotlin", "Java")

coroutine.launch {
       dao.addTalent(talent)

        for (skill in talent.techSkills)
            dao.addTalentSkill(
                Talent_Skill(
                    talent.id_talent,
                    dao.getSkillsIdByName(skill)
                )
            )
   }*/

