package uz.rounded.montekrist.data.repository.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.rounded.montekrist.domain.model.starship.Starship

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM starship")
    fun getStarship(): Flow<List<Starship>>

    @Query("SELECT * FROM starship WHERE name=:name")
    suspend fun getStarshipByName(name: String): Starship?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStarship(starshipModel: Starship)

    @Delete
    suspend fun deleteStarship(starshipModel: Starship)
}