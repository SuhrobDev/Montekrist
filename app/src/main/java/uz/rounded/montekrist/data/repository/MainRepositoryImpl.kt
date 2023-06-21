package uz.rounded.montekrist.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import uz.rounded.montekrist.data.mapper.toModel
import uz.rounded.montekrist.data.repository.datasource.MainRemoteDatasource
import uz.rounded.montekrist.domain.common.Resource
import uz.rounded.montekrist.domain.model.people.PeopleModel
import uz.rounded.montekrist.domain.model.starship.StarshipModel
import uz.rounded.montekrist.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDatasource: MainRemoteDatasource
) : MainRepository {

    private suspend inline fun <T> handleResponse(response: Response<T>): Resource<T> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Resource.Success(body)
            } else {
                Resource.Error("Response body is null")
            }
        } else {
            Resource.Error(response.message())
        }
    }

    override suspend fun getStarships(): Flow<Resource<List<StarshipModel>>> = flow {
        val response = remoteDatasource.getStarship()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it.starship.map { dto -> dto.toModel() }))
            }
        } else if (response.code() == 404) {
            emit(Resource.Error(response.message()))
        } else {
            emit(Resource.Error(response.message()))
        }
    }

    override suspend fun getPeople(): Flow<Resource<List<PeopleModel>>> = flow {
        val response = remoteDatasource.getPeople()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it.peoples.map { dto -> dto.toModel() }))
            }
        } else if (response.code() == 404) {
            emit(Resource.Error(response.message()))
        } else {
            emit(Resource.Error(response.message()))
        }
    }

    override suspend fun search(query: String): Flow<Resource<List<StarshipModel>>> = flow {
        val response = remoteDatasource.search(query = query)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it.starship.map { dto -> dto.toModel() }))
            }
        } else if (response.code() == 404) {
            emit(Resource.Error(response.message()))
        } else {
            emit(Resource.Error(response.message()))
        }
    }
}