package com.example.talenttj.data.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.talenttj.data.room.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // кэширования без пагинации
    @Query("SELECT * FROM user")
    fun getAllFromRoom(): List<User>

    @Query("SELECT * FROM user")
    fun getAll(): PagingSource<Int, User>

    @Query("SELECT * FROM user WHERE userId = :id")
    fun getUserWithID(id: Int): User

    @Query("SELECT professionName FROM Profession WHERE professionId = :id")
    fun getProfessionByID(id: Int): String

    @Query("SELECT TS.skillName FROM Skill AS TS" +
            " INNER JOIN UserSkill ON TS.skillId = UserSkill.idSkill" +
            " WHERE UserSkill.userId = :id")
    fun getSkillsByIdUser(id: Int): List<String>

    @Query("SELECT Skill.skillId FROM Skill WHERE Skill.skillName = :name")
    fun getSkillsIdByName(name: String): Int

    @Query("Select * From user INNER JOIN profession, userskill ON user.professionID = profession.professionId AND user.userId = userskill.userId AND userskill.idSkill = :skillIdI where user.city = :cityNameI AND profession.professionName = :professionNameI")
    fun searchUser(professionNameI: String, skillIdI: Int, cityNameI: String): PagingSource<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<User>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUserSkill(userSkill: UserSkill)

    @Insert
    fun addProfession(profession: Profession)

    @Insert
    fun addSkill(skill: Skill)

    @Transaction
    @Query("SELECT * FROM user")
    fun get(): List<UserSkillPair>

    @Query("DELETE FROM user")
    fun clearUserTable()
}