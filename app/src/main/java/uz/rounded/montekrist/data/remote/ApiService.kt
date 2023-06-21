package uz.rounded.montekrist.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.rounded.montekrist.data.remote.dto.people.PeopleResponseDto
import uz.rounded.montekrist.data.remote.dto.starship.StarshipResponseDto

interface ApiService {
    @GET("people")
    suspend fun getPeople(): Response<PeopleResponseDto>

    @GET("starships")
    suspend fun getStarships(): Response<StarshipResponseDto>

    @GET("starships")
    suspend fun search(@Query("search") query: String): Response<StarshipResponseDto>

}