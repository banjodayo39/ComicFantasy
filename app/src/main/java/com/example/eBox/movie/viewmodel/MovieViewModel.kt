package com.example.eBox.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eBox.adapter.MovieFragmentAdapter
import com.example.eBox.base.BaseViewModel
import com.example.eBox.data.remote.*
import com.example.eBox.data.repo.MovieRepository
import com.example.eBox.util.*
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
    lateinit var allMoviesListLiveData: MutableLiveData<HashMap<String, Double>>

    var listOfPopularMovie = listOf<PopularMovie>()
    var listOfNowShowing = listOf<NowShowing>()
    var listOfTopRatedMovie = listOf<TopRatedMovie>()
    var listOfUpComing = listOf<UpComing>()

    private val allMovieUI = MutableLiveData<ListUIModel<PopularMovie?>>()
    private val allTopRatedMovieUI = MutableLiveData<ListUIModel<TopRatedMovie?>>()
    private val allNowPlayingMovieUI = MutableLiveData<ListUIModel<NowShowing?>>()
    private val allUpcomingMovieUI = MutableLiveData<ListUIModel<UpComing?>>()
    private val allLatestMovieUI = MutableLiveData<DataUIModel<Latest?>>()
    private var movieMapLiveData = MutableLiveData<ArrayList<MovieData>>()
    private val TrailerUI = MutableLiveData<DataUIModel<Trailer>>()


    fun getPopularMovie(): LiveData<ListUIModel<PopularMovie?>> {
        getAllPopularMovieList()
        return allMovieUI
    }

    fun getTopRatedMovie(): LiveData<ListUIModel<TopRatedMovie?>> {
        getAllTopRatedMovieList()
        return allTopRatedMovieUI
    }

    fun getNowPlayingMovie(): LiveData<ListUIModel<NowShowing?>> {
        getAllNowPlayingMovieList()
        return allNowPlayingMovieUI
    }

    fun getUpComingMovie(): LiveData<ListUIModel<UpComing?>> {
        getAllUpcomingMovieList()
        return allUpcomingMovieUI
    }

    fun getLatestMovie(): LiveData<DataUIModel<Latest?>> {
        getAllLatestMovieList()
        return allLatestMovieUI
    }

    fun setAllMovies(movieMap: ArrayList<MovieData>) {
        movieMapLiveData.value = movieMap
    }

    fun getAllMovies(): LiveData<ArrayList<MovieData>> {
        return movieMapLiveData
    }


    private fun getAllPopularMovieList() {
        addDisposable {
            allMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getPopularMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe(
                { it1 ->
                    Log.e("vm-popular", it1.toString())
                    if (it1 != null) {
                        it1.map {
                            repo.savePopularMovie(it!!)
                        }
                        allMovieUI.postValue(ListUIModel(list = it1, isLoading = false))
                    } else
                        allMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
                },
                {
                    allMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
                })!!

        }
    }

    private fun getAllTopRatedMovieList() {
        addDisposable {
            allTopRatedMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getTopRatedMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe(
                { it1 ->
                    Log.e("vm-top-rating", it1.toString())
                    if (it1 != null) {
                            repo.saveTopRatedMovie(it1 as List<TopRatedMovie>)
                        allTopRatedMovieUI.postValue(ListUIModel(list = it1, isLoading = false))
                    } else
                        allTopRatedMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
                },
                {
                    allTopRatedMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
                })!!

        }
    }

    private fun getAllNowPlayingMovieList() {
        addDisposable {
            allNowPlayingMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getNowPlayingMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe(
                { it1 ->
                    Log.e("vm-NowPlaying", it1.toString())
                    if (it1 != null) {
                        it1.map {
                            repo.saveNowPlayingMovie(it!!)
                        }
                        allNowPlayingMovieUI.postValue(ListUIModel(list = it1, isLoading = false))

                    } else
                        allNowPlayingMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
                },
                {
                    allNowPlayingMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
                })!!
        }
    }

    private fun getAllUpcomingMovieList() {
        addDisposable {
            allUpcomingMovieUI.postValue(ListUIModel(isLoading = true))
            repo.getUpcomingMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe(
                { it1 ->
                    Log.e("MovieViewModel", it1.toString())
                    if (it1 != null) {
                        it1.map {
                            repo.saveUpcomingMovie(it!!)
                        }
                        allUpcomingMovieUI.postValue(ListUIModel(list = it1, isLoading = false))
                    } else
                        allUpcomingMovieUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
                },
                {
                    allUpcomingMovieUI.postValue(ListUIModel(error = processNetworkError(it)))
                })!!

        }
    }

    private fun getAllLatestMovieList() {
        addDisposable {
            allLatestMovieUI.postValue(DataUIModel(isLoading = true))
            repo.getLatestMovieList().subscribe({ it1 ->
                Log.e("latest-viewModel", it1.toString())
                if (it1 != null) {
                    repo.saveLatestMovie(it1)
                    allLatestMovieUI.postValue(DataUIModel(data = it1, isLoading = false))
                } else
                    allLatestMovieUI.postValue(DataUIModel(error = getMsgFromErrBody("latest_error")))
            }, {
                allLatestMovieUI.postValue(DataUIModel(error = processNetworkError(it)))
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

}





