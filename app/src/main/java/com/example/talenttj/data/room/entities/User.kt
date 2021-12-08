package com.example.talenttj.data.room.entities

import android.app.appsearch.GetByDocumentIdRequest
import android.provider.ContactsContract
import androidx.room.*
import com.example.talenttj.data.ProfessionAndSkill
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(
    tableName = "user"/*, foreignKeys = [ForeignKey(
    entity = Profession::class,
    parentColumns = ["professionId"],
    childColumns = ["professionID"]
)]*/, indices = [Index("city")]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var userId: Int,
    @SerializedName("first_name")
    var name: String,
    @SerializedName("last_name")
    var surname: String,
    var email: String? = null,
    @SerializedName("gender")
    var city: String = "",
    var country: String? = "Tajikistan",
    @SerializedName("date_of_birth")
    var birthDay: String,
    @SerializedName("username")
    var education: String = "",
    @ColumnInfo(index = true)
    // для некоторых данные которые не из сервера добавил рандомние значения
    var professionID: Int = ProfessionAndSkill.getDefaultProfessions().shuffled()[5].professionId,
    var working_status: Boolean = listOf(true, false).shuffled()[0],
    @SerializedName("social_insurance_number")
    var userDesc: String = "",
    @SerializedName("phone_number")
    var phone: String? = "",
    var gitHub: String? = "https://github.com/Ahatzoda-Tehronshoh",
    var linkedIn: String? = "",
    var faceBook: String? = "",
    @SerializedName("avatar")
    var Image: String = "",
) {
    @Ignore
    // для некоторых данные которые не из сервера добавил рандомние значения
    var skills: List<Int> = listOf(
        ProfessionAndSkill.getDefaultTechSkill().shuffled()[3].skillId,
        ProfessionAndSkill.getDefaultTechSkill().shuffled()[5].skillId
    )
}