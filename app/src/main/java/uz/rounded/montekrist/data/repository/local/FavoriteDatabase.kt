package uz.rounded.montekrist.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.rounded.montekrist.data.repository.local.dao.FavoriteDao
import uz.rounded.montekrist.domain.model.starship.Starship

@Database(entities = [Starship::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favoriteStarshipDao: FavoriteDao

    companion object {
        const val DB_NAME = "favorite_db"
    }
}