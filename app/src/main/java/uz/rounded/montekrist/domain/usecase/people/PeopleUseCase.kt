package uz.rounded.montekrist.domain.usecase.people

import kotlinx.coroutines.flow.Flow
import uz.rounded.montekrist.domain.common.Resource
import uz.rounded.montekrist.domain.model.people.PeopleModel
import uz.rounded.montekrist.domain.repository.MainRepository
import javax.inject.Inject

class PeopleUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<PeopleModel>>> {
        return repository.getPeople()
    }
}