package uz.rounded.montekrist.data.repository.datasource

import retrofit2.Response
import uz.rounded.montekrist.data.remote.ApiService
import uz.rounded.montekrist.data.remote.dto.people.PeopleResponseDto
import uz.rounded.montekrist.data.remote.dto.starship.StarshipResponseDto
import javax.inject.Inject

class MainRemoteDatasourceImpl @Inject constructor(
    private val apiService: ApiService
) : MainRemoteDatasource {

    override suspend fun getPeople(): Response<PeopleResponseDto> {
        return apiService.getPeople()
    }

    override suspend fun getStarship(): Response<StarshipResponseDto> {
        return apiService.getStarships()
    }

    override suspend fun search(query: String): Response<StarshipResponseDto> {
        return apiService.search(query = query)
    }
}

