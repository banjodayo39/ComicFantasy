package com.example.eBox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eBox.adapter.ComicFragmentAdapterclass
import com.example.eBox.base.BaseViewModel
import com.example.eBox.data.remote.ComicResults
import com.example.eBox.data.repo.ComicRepository
import com.example.eBox.util.ListUIModel
import com.example.eBox.util.SchedulerProvider
import com.example.eBox.util.getMsgFromErrBody
import com.example.eBox.util.processNetworkError
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

