package com.example.talenttj.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.talenttj.data.ProfessionAndSkill
import com.example.talenttj.data.room.dao.UserDao
import com.example.talenttj.data.room.entities.Profession
import com.example.talenttj.data.room.entities.Skill
import com.example.talenttj.data.room.entities.User
import com.example.talenttj.data.room.entities.UserSkill
import java.util.concurrent.Executors

@Database(entities = [User::class, Profession::class, Skill::class, UserSkill::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "1_database_user"
                ).addCallback(object: RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                            INSTANCE?.let {
                                val dao = it.userDao()
                                Executors.newSingleThreadScheduledExecutor().execute {
                                    for (item in ProfessionAndSkill.getDefaultProfessions())
                                        dao.addProfession(item)
                                    for (item in ProfessionAndSkill.getDefaultTechSkill())
                                        dao.addSkill(item)
                                }
                            }
                    }
                }).build().also {
                    INSTANCE = it
                }
            }
        }

    }
}

