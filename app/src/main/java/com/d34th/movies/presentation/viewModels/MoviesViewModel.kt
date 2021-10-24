package com.d34th.movies.presentation.viewModels

import android.app.Application
import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.*
import coil.ImageLoader
import coil.request.ImageRequest
import com.d34th.movies.data.models.Movie
import com.d34th.movies.data.models.MovieEntity
import com.d34th.movies.data.models.MovieList
import com.d34th.movies.repository.MovieRepository
import com.d34th.movies.utils.Constants
import com.d34th.movies.utils.Resource
import com.d34th.movies.utils.ResultMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MovieRepository,
    private val imageLoader: ImageLoader,
    application: Application,
) : AndroidViewModel(application) {

    private val _movieList=MutableLiveData<Resource<ResultMovies>>()
    val movieList:LiveData<Resource<ResultMovies>> get() = _movieList

    private var updateUpcoming:Job?=null
    private var updateRate:Job?=null
    private var updatePopular:Job?=null

    init {
        fetchMainScreenMovies(application)
    }

    fun fetchMainScreenMovies(context: Context) = viewModelScope.launch {
        _movieList.value=Resource.Loading()
        try {
            _movieList.value=Resource.Success(getListsMovies().also { triple ->
                val (upcoming,rate,popular)=triple
                updateUpcoming?.cancel()
                updateRate?.cancel()
                updatePopular?.cancel()

                updateUpcoming=async { upcoming.forEach { updateMovieImages(context,it) } }
                updateRate=async { rate.forEach { updateMovieImages(context,it) } }
                updatePopular=async { popular.forEach { updateMovieImages(context,it) } }
            })

        } catch (e: Exception) {
            _movieList.value=Resource.Failure(e)
            if (e is CancellationException) {
                throw e
            }
        }
    }


    private suspend fun getListsMovies() = coroutineScope {
        val listUpcoming = async(Dispatchers.IO) { repo.getUpcomingMovies() }
        val listTopRate = async(Dispatchers.IO) { repo.getTopRateMovies() }
        val listPopular = async(Dispatchers.IO) { repo.getPopularMovies() }
        Triple(
            listUpcoming.await(),
            listTopRate.await(),
            listPopular.await()
        )
    }

    fun updateMovieImages(context:Context,movie:MovieEntity)=viewModelScope.launch {
        with(movie){
            if(bitmapPoster==null){
                val bitmapPost = async {
                    val imagePoster =
                        ImageRequest.Builder(context).data("${Constants.PREFIX_IMG}${urlImagePoster}").build()
                    val resultPoster = imageLoader.execute(imagePoster)
                    resultPoster.drawable?.toBitmap()
                }
                val bitmapBack = async {
                    val imageBack =
                        ImageRequest.Builder(context).data("${Constants.PREFIX_IMG}${urlImageBackground}").build()
                    val resultBack = imageLoader.execute(imageBack)
                    resultBack.drawable?.toBitmap()
                }
                bitmapPoster=bitmapPost.await()
                bitmapBackground=bitmapBack.await()
                repo.updateMovie(movie)
            }

        }
    }
}