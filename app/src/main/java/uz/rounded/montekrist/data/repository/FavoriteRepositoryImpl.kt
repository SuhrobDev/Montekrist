package uz.rounded.montekrist.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import uz.rounded.montekrist.data.repository.local.dao.FavoriteDao
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val starshipDao: FavoriteDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FavoriteRepository {
    override fun getStarship(): Flow<List<Starship>> {
        return starshipDao.getStarship()
    }

    override suspend fun getStarshipById(name: String): Starship? {
        return withContext(ioDispatcher) {
            starshipDao.getStarshipByName(name = name)
        }
    }

    override suspend fun insertStarship(starship: Starship) = channelFlow<String> {
        val itemCount = starshipDao.getStarshipByName(starship.name)
        if (itemCount?.name.isNullOrEmpty()) {
            starshipDao.insertStarship(starship)
            this.channel.send("saved!")
        } else {
//            starshipDao.deleteStarship(starship)
            deleteStarship(starship)
            this.channel.send("deleted!")

            // Handle duplicate item
            // For example, show an error message or perform a different action
        }
    }

    override suspend fun deleteStarship(starship: Starship) {
        withContext(ioDispatcher) {
            starshipDao.deleteStarship(starship)
        }
    }
}