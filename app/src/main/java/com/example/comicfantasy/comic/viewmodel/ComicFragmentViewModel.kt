package com.example.comicfantasy.comic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.adapter.ComicFragmentAdapterclass
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.ComicResults
import com.example.comicfantasy.data.repo.ComicRepository
import com.example.comicfantasy.util.ListUIModel
import com.example.comicfantasy.util.SchedulerProvider
import com.example.comicfantasy.util.getMsgFromErrBody
import com.example.comicfantasy.util.processNetworkError
import javax.inject.Inject

class ComicFragmentViewModel @Inject
constructor(
    private val repo: ComicRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {

    private val allComicUI = MutableLiveData<ListUIModel<ComicResults?>>()
    lateinit var adapter: ComicFragmentAdapterclass
    var comicList = listOf<ComicResults>()

    fun getComic(): LiveData<ListUIModel<ComicResults?>> {
        getAllComicFromDb()
        return allComicUI
    }

    private fun getAllComicList() {
        addDisposable {
            allComicUI.postValue(ListUIModel(isLoading = true))
            repo.getComicListApi().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                if (!it.isNullOrEmpty()) {
                    repo.saveComic(it as List<ComicResults>)
                    allComicUI.postValue(ListUIModel(list = it, isLoading = false))
                } else
                    allComicUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allComicUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!


        }
    }
    // db function
    private fun getAllComicFromDb() {
        addDisposable {
            allComicUI.postValue(ListUIModel(isLoading = true))
            repo.getComicList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                if (!it.isNullOrEmpty()) {
                    repo.saveComic(it as List<ComicResults>)
                    allComicUI.postValue(ListUIModel(list = it, isLoading = false))
                    getAllComicList()
                } else
                    allComicUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allComicUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!


        }
    }
                /*.subscribeOn(provider.io())
                .observeOn(provider.io())
                .subscribe({ it ->
                    if (!dbBalance.isEmpty()) {
                        balanceLiveData.postValue(
                            DataUI(
                                false,
                                AccountBalance(dbBalance), null
                            )
                        )
                    }

                    getAccountBalance()

                }, { error ->
                    error.printStackTrace()
                    getAccountBalance()
                })
        )*/


}
