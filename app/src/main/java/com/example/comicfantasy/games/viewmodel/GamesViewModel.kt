package com.example.comicfantasy.games.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.MovieDataResponse
import com.example.comicfantasy.data.remote.Trivia
import com.example.comicfantasy.data.repo.GamesRepository
import com.example.comicfantasy.data.repo.MovieRepository
import com.example.comicfantasy.util.DataUIModel
import com.example.comicfantasy.util.SchedulerProvider
import com.example.comicfantasy.util.getMsgFromErrBody
import com.example.comicfantasy.util.processNetworkError
import javax.inject.Inject

class GamesViewModel @Inject
constructor(
    private val repo: GamesRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {

    private val allGamesUI = MutableLiveData<DataUIModel<Trivia>>()

    fun getGames(): LiveData<DataUIModel<Trivia>> {
        getAllMovieList()
        return allGamesUI
    }

    private fun getAllMovieList() {
        addDisposable {
            allGamesUI.postValue(DataUIModel(isLoading = true))
            repo.getTrivia().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null)
                    allGamesUI.postValue(DataUIModel(data = it))
                else
                    allGamesUI.postValue(DataUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allGamesUI.postValue(DataUIModel(error = processNetworkError(it)))
            })!!

        }
    }
}