package uz.rounded.montekrist.domain.usecase.starship

import kotlinx.coroutines.flow.Flow
import uz.rounded.montekrist.domain.common.Resource
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.repository.MainRepository
import javax.inject.Inject

class StarshipUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<StarshipModel>>> {
        return repository.getStarships()
    }

    suspend fun search(query: String): Flow<Resource<List<StarshipModel>>> {
        return repository.search(query = query)
    }
}