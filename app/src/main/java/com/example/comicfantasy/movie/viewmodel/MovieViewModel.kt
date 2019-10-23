package com.example.comicfantasy.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.remote.MovieDataResponse
import com.example.comicfantasy.data.repo.MovieRepository
import com.example.comicfantasy.util.DataUIModel
import com.example.comicfantasy.util.SchedulerProvider
import com.example.comicfantasy.util.getMsgFromErrBody
import com.example.comicfantasy.util.processNetworkError
import javax.inject.Inject

class MovieViewModel@Inject
constructor(
    private val repo: MovieRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {


    private val allMovieUI = MutableLiveData<DataUIModel<MovieDataResponse>>()


    fun getMovie(): LiveData<DataUIModel<MovieDataResponse>> {
        getAllMovieList()
        return allMovieUI
    }

    private fun getAllMovieList(){
        addDisposable {
            allMovieUI.postValue(DataUIModel(isLoading = true))
            repo.getMovieList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel",it.toString())
                if(it != null)
                    allMovieUI.postValue(DataUIModel(data = it))
                else
                    allMovieUI.postValue(DataUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allMovieUI.postValue(DataUIModel(error = processNetworkError(it)))
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





