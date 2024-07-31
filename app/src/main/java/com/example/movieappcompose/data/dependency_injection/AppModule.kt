package com.example.movieappcompose.data.dependency_injection

import com.example.movieappcompose.data.remote.retrofit.MovieApi
import com.example.movieappcompose.data.repository.MovieRepoImpl
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieApi():MovieApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi):MovieRepository{
        return MovieRepoImpl(api)
    }
}