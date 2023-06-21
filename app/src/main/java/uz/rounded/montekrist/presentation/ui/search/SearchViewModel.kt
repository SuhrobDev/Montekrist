package uz.rounded.montekrist.presentation.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.usecase.starship.StarshipUseCase
import uz.rounded.montekrist.presentation.common.UIListState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: StarshipUseCase) : ViewModel() {
    private val _searchStarship = MutableStateFlow(UIListState<StarshipModel>())
    val searchStarship = _searchStarship.asStateFlow()

    fun searchStarship(query: String): Flow<List<StarshipModel>> {
        if (query.isEmpty()) return emptyFlow()
        return flow {
            useCase.search(query)
                .catch { e ->
                    emit(emptyList<StarshipModel>())
                }
                .collect {
                    it.data?.let { it1 -> emit(it1) }
                }

        }
    }

}