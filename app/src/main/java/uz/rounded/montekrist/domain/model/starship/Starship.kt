package uz.rounded.montekrist.domain.model.starship

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starship")
data class Starship(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    val films: List<String>,
    val model: String,
    @ColumnInfo(name = "name")
    val name: String,
    val passengers: String,
    val starship_class: String,
    val url: String,
    val cost_in_credits: String,
)