package uz.rounded.montekrist.domain.usecase.favorite

import javax.inject.Inject

class FavoriteUseCases @Inject constructor(
    val deleteNote: DeleteFavorite,
    val addNote: AddStarship,
    val getNote: GetStarship
)