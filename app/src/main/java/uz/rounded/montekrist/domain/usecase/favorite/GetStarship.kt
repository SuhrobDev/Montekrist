package uz.rounded.montekrist.domain.usecase.favorite

import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class GetStarship @Inject constructor(private val repository: FavoriteRepository) {

    fun getStarship() = repository.getStarship()

    operator fun invoke() = repository.getStarship()

    suspend fun searchStarship(name: String): Starship? {
        return repository.getStarshipById(name = name)
    }


}