package com.example.comicfantasy.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.adapter.MovieFragmentAdapter
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

    lateinit var upcomingMovieAdapter: MovieFragmentAdapter
    lateinit var popularMovieAdapter: MovieFragmentAdapter
    lateinit var nowPlayingMovieAdapter: MovieFragmentAdapter
    lateinit var topRatedMovieAdapter: MovieFragmentAdapter
    lateinit var latestMovieAdapter: MovieFragmentAdapter

    private val allMovieUI = MutableLiveData<ListUIModel<MovieResult?>>()
    private val allTopRatedMovieUI = MutableLiveData<ListUIModel<MovieResult?>>()
    private val allNowPlayingMovieUI = MutableLiveData<ListUIModel<MovieResult?>>()
    private val allUpcomingMovieUI = MutableLiveData<ListUIModel<MovieResult?>>()
    private val allLatestMovieUI = MutableLiveData<ListUIModel<MovieResult?>>()
    private val TrailerUI = MutableLiveData<DataUIModel<Trailer>>()


    fun getPopularMovie(): LiveData<ListUIModel<MovieResult?>> {
        getAllPopularMovieList()
        return allMovieUI
    }

    fun getTopRatedMovie(): LiveData<ListUIModel<MovieResult?>> {
        getAllTopRatedMovieList()
        return allTopRatedMovieUI
    }

    fun getNowPlayingMovie(): LiveData<ListUIModel<MovieResult?>> {
        getAllNowPlayingMovieList()
        return allNowPlayingMovieUI
    }

    fun getUpComingMovie(): LiveData<ListUIModel<MovieResult?>> {
        getAllUpcomingMovieList()
        return allUpcomingMovieUI
    }

    fun getLatestMovie(): LiveData<ListUIModel<MovieResult?>> {
        getAllLatestMovieList()
        return allLatestMovieUI
    }

    private fun getAllPopularMovieList() {
        addDisposable {
            allMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getPopularMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null) {
                    allMovieUI.postValue(ListUIModel(list = it,isLoading = false))
                    repo.savePopularMovie(it as List<MovieResult>)
                } else
                    allMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }
    }

    private fun getAllTopRatedMovieList() {
        addDisposable {
            allTopRatedMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getTopRatedMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null) {
                    allTopRatedMovieUI.postValue(ListUIModel(list = it,isLoading = false))
                    repo.saveTopRatedMovie(it as List<MovieResult>)
                } else
                    allTopRatedMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allTopRatedMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }
    }

    private fun getAllNowPlayingMovieList() {
        addDisposable {
            allNowPlayingMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getNowPlayingMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null) {
                    allNowPlayingMovieUI.postValue(ListUIModel(list = it))
                    repo.saveNowPlayingMovie(it as List<MovieResult>)
                } else
                    allNowPlayingMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allNowPlayingMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }
    }

    private fun getAllUpcomingMovieList() {
        addDisposable {
            allUpcomingMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getUpcomingMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null) {
                    allUpcomingMovieUI.postValue(ListUIModel(list = it, isLoading = false))
                    repo.saveUpcomingMovie(it as List<MovieResult>)
                } else
                    allUpcomingMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allUpcomingMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }
    }

    private fun getAllLatestMovieList() {
        addDisposable {
            allLatestMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getLatestMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null) {
                    allLatestMovieUI.postValue(ListUIModel(list = it, isLoading = false))
                    repo.saveLatestMovie(it as List<MovieResult>)
                } else
                    allLatestMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allLatestMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
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
             repo.getPopularMovieList(id).subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
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





