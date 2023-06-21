package uz.rounded.montekrist.domain.usecase.favorite

import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class AddStarship @Inject constructor(private val noteRepository: FavoriteRepository) {

    suspend operator fun invoke(starship: Starship) = channelFlow {
        noteRepository.insertStarship(starship).collectLatest { this.channel.send(it) }
    }

}