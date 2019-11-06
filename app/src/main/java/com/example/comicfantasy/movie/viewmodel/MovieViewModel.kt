package com.example.comicfantasy.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.*
import com.example.comicfantasy.data.repo.MovieRepository
import com.example.comicfantasy.util.*
import javax.inject.Inject

class MovieViewModel @Inject
constructor(
    private val repo: MovieRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {


    private val allMovieUI = MutableLiveData<ListUIModel<MovieResult?>>()
    private val TrailerUI = MutableLiveData<DataUIModel<Trailer>>()


    fun getMovie(): LiveData<ListUIModel<MovieResult?>> {
        getAllMovieList()
        return allMovieUI
    }

    private fun getAllMovieList() {
        addDisposable {
            allMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null) {
                    allMovieUI.postValue(ListUIModel(list = it))
                    repo.saveMovie(it as List<MovieResult>)
                } else
                    allMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }
    }

    fun getTrailer(id: Int): LiveData<DataUIModel<Trailer>> {
        getAllMovieTrailer(id)
        return TrailerUI
    }

    private fun getAllMovieTrailer(id: Int) {
        addDisposable {
            TrailerUI.postValue(DataUIModel(isLoading = true))
            repo.getMovieTrailerFromApi(id).subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe(
                {
                    Log.e("MovieViewModel", it.toString())
                    if (it != null)
                        TrailerUI.postValue(DataUIModel(data = it))
                    else
                        TrailerUI.postValue(DataUIModel(error = getMsgFromErrBody("error_here")))
                },
                {
                    TrailerUI.postValue(DataUIModel(error = processNetworkError(it)))
                })!!

        }
    }

    /* fun getComicStory(): LiveData<DataUIModel<DataResponse>> {
         getAllComicList()
         return allComicUI
     }

     private fun getComicStory(id:Int){
         addDisposable {
             allComicUI.postValue(DataUIModel(isLoading = true))
             repo.getMovieList(id).subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                 Log.e("ComicFragmentViewModel",it.toString())
                 if(it != null)
                     allComicUI.postValue(DataUIModel(data = it))
                 else
                     allComicUI.postValue(DataUIModel(error = getMsgFromErrBody("error_here")))
             }, {
                 allComicUI.postValue(DataUIModel(error = processNetworkError(it)))
             })!!
  }
  }
         }*/
}





