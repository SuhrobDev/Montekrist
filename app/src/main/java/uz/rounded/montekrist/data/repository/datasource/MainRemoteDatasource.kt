package uz.rounded.montekrist.data.repository.datasource

import retrofit2.Response
import uz.rounded.montekrist.data.remote.dto.people.PeopleResponseDto
import uz.rounded.montekrist.data.remote.dto.starship.StarshipResponseDto

interface MainRemoteDatasource {
    suspend fun getPeople(): Response<PeopleResponseDto>

    suspend fun getStarship(): Response<StarshipResponseDto>
    suspend fun search(query: String): Response<StarshipResponseDto>
}