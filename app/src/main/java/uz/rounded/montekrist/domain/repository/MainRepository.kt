package uz.rounded.montekrist.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.common.Resource
import uz.rounded.montekrist.domain.model.people.PeopleModel

interface MainRepository {
    suspend fun getStarships(): Flow<Resource<List<StarshipModel>>>

    suspend fun getPeople(): Flow<Resource<List<PeopleModel>>>

    suspend fun search(query: String): Flow<Resource<List<StarshipModel>>>
}