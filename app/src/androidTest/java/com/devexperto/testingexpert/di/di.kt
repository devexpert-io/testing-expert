package com.devexperto.testingexpert.di

import android.app.Application
import androidx.room.Room
import com.devexperto.testingexpert.data.datasource.*
import com.devexperto.testingexpert.data.local.AppDatabase
import com.devexperto.testingexpert.data.local.BoardDao
import com.devexperto.testingexpert.data.local.ScoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton


@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDataModule::class, AppExtrasModule::class]
)
@Module
object TestAppDataModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        AppDatabase::class.java
    )
        .setTransactionExecutor(Dispatchers.Main.asExecutor())
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideBoardDataSource(boardDao: BoardDao): BoardLocalDataSource =
        RoomBoardDataSource(boardDao)

    @Provides
    fun provideScoreDataSource(scoreDao: ScoreDao): ScoreLocalDataSource =
        RoomScoreDataSource(scoreDao)

    @Provides
    @Singleton
    fun provideGameRemoteSourceFake(): GamesRemoteDataSourceFake = GamesRemoteDataSourceFake()

    @Provides
    fun provideGameDataSource(fake: GamesRemoteDataSourceFake): GamesRemoteDataSource = fake

}