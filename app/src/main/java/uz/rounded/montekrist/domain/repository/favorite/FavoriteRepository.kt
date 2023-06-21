package uz.rounded.montekrist.domain.repository.favorite

import kotlinx.coroutines.flow.Flow
import uz.rounded.montekrist.domain.model.starship.Starship

interface FavoriteRepository {

    fun getStarship(): Flow<List<Starship>>

    suspend fun getStarshipById(name: String): Starship?

    suspend fun insertStarship(starship: Starship): Flow<String>

    suspend fun deleteStarship(starship: Starship)

}