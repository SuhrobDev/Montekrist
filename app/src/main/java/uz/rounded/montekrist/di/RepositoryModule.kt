package uz.rounded.montekrist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.rounded.montekrist.data.repository.FavoriteRepositoryImpl
import uz.rounded.montekrist.data.repository.MainRepositoryImpl
import uz.rounded.montekrist.data.repository.datasource.MainRemoteDatasource
import uz.rounded.montekrist.data.repository.local.FavoriteDatabase
import uz.rounded.montekrist.domain.repository.MainRepository
import uz.rounded.montekrist.domain.repository.favorite.FavoriteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFavoriteRepository(db: FavoriteDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(db.favoriteStarshipDao)
    }

    @Singleton
    @Provides
    fun provideMainRepository(mainRemoteDataSource: MainRemoteDatasource): MainRepository {
        return MainRepositoryImpl(mainRemoteDataSource)
    }


}