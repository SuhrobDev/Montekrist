package uz.rounded.montekrist.domain.usecase.favorite

import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class DeleteFavorite @Inject constructor(private val repository: FavoriteRepository) {

    suspend operator fun invoke(note: Starship) {
        repository.deleteStarship(note)
    }

}