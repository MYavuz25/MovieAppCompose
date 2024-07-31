package com.example.movieappcompose.domain.use_case.get_movie_detail

import com.example.movieappcompose.data.remote.dto.toMovie
import com.example.movieappcompose.domain.model.Movie
import com.example.movieappcompose.domain.model.MovieDetail
import com.example.movieappcompose.domain.repository.MovieRepository
import com.example.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class getMovieDetailUseCase @Inject constructor(private  val repository: MovieRepository) {
    fun getMovie(imdbID:String): Flow<Resource<MovieDetail>> = flow{
        try {
            emit(Resource.Loading())
            val movie=repository.getMovieDetail(imdbID)
            emit(Resource.Success(movie.toMovie()))

        }catch (e: IOError){
            emit(Resource.Error(e.localizedMessage?:"ERROR"))
        }
    }
}