package com.example.movieappcompose.domain.use_case.get_movies

import com.example.movieappcompose.data.remote.dto.toMovie
import com.example.movieappcompose.domain.model.Movie
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository){


    fun getAllMovies(search:String):Flow<Resource<List<Movie>>> = flow{
        try {
            emit(Resource.Loading())
            val movieList=repository.getMovie(search)
            if (movieList.Response.equals("True")){
                emit(Resource.Success(movieList.toMovie()))

            }else{
                emit(Resource.Error("No Movie Found !!!"))
            }

        }catch (e:IOError){
            emit(Resource.Error(e.localizedMessage?:"ERROR"))
        }
    }
}