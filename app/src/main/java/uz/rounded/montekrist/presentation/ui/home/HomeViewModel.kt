package uz.rounded.montekrist.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.usecase.favorite.FavoriteUseCases
import uz.rounded.montekrist.domain.usecase.starship.StarshipUseCase
import uz.rounded.montekrist.presentation.base.BaseViewModel
import uz.rounded.montekrist.presentation.common.UIListState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: StarshipUseCase,
    private val dbUseCase: FavoriteUseCases
) : BaseViewModel() {
    private val _starship = MutableStateFlow(UIListState<StarshipModel>())
    val starship = _starship.asStateFlow()

    init {
        getStarship()
    }

    private fun getStarship() {
        getDataList({ useCase.invoke() }, _starship)
    }

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    suspend fun insertStarship(starship: Starship) = channelFlow<String> {
        viewModelScope.launch {
            dbUseCase.addNote.invoke(starship = starship).collectLatest {
                _data.value = it
                Log.d("reloaddddd", "state: $it")
            }
            Log.d("reloaddddd", "insertStarship: $starship")
        }
    }

}