package uz.rounded.montekrist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.rounded.montekrist.data.remote.ApiService
import uz.rounded.montekrist.data.repository.datasource.MainRemoteDatasource
import uz.rounded.montekrist.data.repository.datasource.MainRemoteDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMainDataSource(mainApiService: ApiService): MainRemoteDatasource {
        return MainRemoteDatasourceImpl(mainApiService)
    }



}