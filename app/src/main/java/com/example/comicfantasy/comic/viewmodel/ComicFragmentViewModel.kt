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
        getAllComicList()
        getAllComicFromDb()
        return allComicUI
    }

    fun getAllComicList() {
        allComicUI.postValue(ListUIModel(isLoading = true))
        addDisposable {
            repo.getComicListFromDb()
                .subscribeOn(provider.io())
                .observeOn(provider.ui())
                .subscribe({
                if (!it.isNullOrEmpty()) {
                    allComicUI.postValue(ListUIModel(list = it, isLoading = false))
                } else
                    allComicUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allComicUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!
        }
    }

    private fun getAllComicFromDb() {
        addDisposable {
            repo.getComicListApi()
                .subscribeOn(provider.io())
                .observeOn(provider.ui())
                .subscribe({ db ->
                    if (db.isNotEmpty()) {
                        repo.saveComic(db)

                        allComicUI.postValue(
                            ListUIModel(
                                false,
                                db, null
                            )
                        )
                    }
                    getAllComicList()
                }, { error ->
                    error.printStackTrace()
                    getAllComicList()
                })
        }
    }
}

