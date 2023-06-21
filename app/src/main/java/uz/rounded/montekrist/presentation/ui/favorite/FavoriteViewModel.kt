package uz.rounded.montekrist.presentation.ui.favorite

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.usecase.favorite.FavoriteUseCases
import uz.rounded.montekrist.presentation.base.BaseViewModel
import uz.rounded.montekrist.presentation.common.UIListState
import uz.rounded.montekrist.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val noteUseCases: FavoriteUseCases, savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _favorite = MutableStateFlow(UIListState<Starship>())
    val favorite = _favorite.asStateFlow()

    private val _search = MutableStateFlow(UIObjectState<Starship>())
    val search = _search.asStateFlow()

    private val list = listOf<StarshipModel>()

    private var currentStarshipId: String? = null

    init {
//        getStarship()
//        savedStateHandle.get<String>("name")?.let { noteId ->
//            if (noteId != "") {
//                viewModelScope.launch {
//                    noteUseCases.getNote.searchStarship(name = noteId)?.also { note ->
//                        currentStarshipId = note.name
//                    }
//                }
//            }
//        }

    }

    private val _myListFlow = flow {
        // Perform your data retrieval or processing here
        val myList = noteUseCases.getNote.getStarship()
        emit(myList)
    }

    val myListFlow: Flow<Flow<List<Starship>>> = _myListFlow

    suspend fun search(query: String) {
        viewModelScope.launch {
            val result = noteUseCases.getNote.searchStarship(query)
            _search.value.copy("", result, false)
        }
    }

//    private fun getStarship() {
//        viewModelScope.launch {
//            noteUseCases.getNote.getStarship().collectLatest {
//
//                Log.d("reloaddddd", "getStarship: $it")
//                _favorite.value.copy("error detected", it, false)
////                it.map { starship ->
////                    _favorite.value.copy(
////                        "some problem",
////                        listOf(
////                            StarshipModel(
////                                _id = starship.id,
////                                model = starship.model,
////                                name = starship.name,
////                                passengers = starship.passengers,
////                                starship_class = starship.starship_class,
////                                url = starship.url,
////                                cost_in_credits = starship.cost_in_credits
////                            )
////                        ),
////                        false
////                    )
////                }
//            }
//        }
//    }


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

//    fun onEvent(event: AddEditNoteEvent) {
//        when (event) {
//            is AddEditNoteEvent.SaveNote -> {
//                viewModelScope.launch {
//                    try {
//                        noteUseCases.addNote(
//                            StarshipModel(
//                                _id = _favorite.value.data?._id ?: "",
//                                films = _favorite.value.data?.films ?: emptyList(),
//                                model = _favorite.value.data?.model ?: "",
//                                name = _favorite.value.data?.name ?: "",
//                                passengers = _favorite.value.data?.passengers ?: "",
//                                starship_class = _favorite.value.data?.starship_class ?: "",
//                                url = _favorite.value.data?.url ?: "",
//                                cost_in_credits = _favorite.value.data?.cost_in_credits ?: ""
//                            )
//                        )
//                        _eventFlow.emit(UiEvent.SaveNote)
//                    } catch (e: InvalidNoteException) {
//                        _eventFlow.emit(
//                            UiEvent.ShowSnackbar(
//                                message = e.message ?: "Couldn't save note"
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }

    sealed class AddEditNoteEvent {
        object SaveNote : AddEditNoteEvent()
    }
}