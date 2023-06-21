package uz.rounded.montekrist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.rounded.montekrist.data.repository.local.FavoriteDatabase
import uz.rounded.montekrist.domain.repository.favorite.FavoriteRepository
import uz.rounded.montekrist.domain.usecase.favorite.AddStarship
import uz.rounded.montekrist.domain.usecase.favorite.DeleteFavorite
import uz.rounded.montekrist.domain.usecase.favorite.FavoriteUseCases
import uz.rounded.montekrist.domain.usecase.favorite.GetStarship
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase =
        Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            FavoriteDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFavoriteUseCases(favoriteRepository: FavoriteRepository): FavoriteUseCases {
        return FavoriteUseCases(
            deleteNote = DeleteFavorite(favoriteRepository),
            addNote = AddStarship(favoriteRepository),
            getNote = GetStarship(favoriteRepository)
        )
    }

    @Singleton
    @Provides
    fun provideFavoriteStarshipDao(favoriteDatabase: FavoriteDatabase) =
        favoriteDatabase.favoriteStarshipDao

}