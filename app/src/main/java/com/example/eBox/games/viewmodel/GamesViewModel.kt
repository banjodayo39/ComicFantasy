package com.example.eBox.games.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eBox.base.BaseViewModel
import com.example.eBox.data.remote.GamesResult
import com.example.eBox.data.repo.GamesRepository
import com.example.eBox.util.*
import javax.inject.Inject

class GamesViewModel @Inject
constructor(
    private val repo: GamesRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {

    private val allGamesUI = MutableLiveData<ListUIModel<GamesResult>>()

    fun getGames(): LiveData<ListUIModel<GamesResult>> {
        getAllMovieList()
        return allGamesUI
    }

    private fun getAllMovieList() {
        addDisposable {
            allGamesUI.postValue(ListUIModel(isLoading = true))
            repo.getTrivia().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("MovieViewModel", it.toString())
                if (it != null)
                    allGamesUI.postValue(ListUIModel(list = it, isLoading = false))
                else
                    allGamesUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allGamesUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }
    }
}