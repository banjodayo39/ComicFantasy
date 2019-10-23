package com.example.comicfantasy.comic.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.repo.ComicRepository
import com.example.comicfantasy.util.DataUIModel
import com.example.comicfantasy.util.SchedulerProvider
import com.example.comicfantasy.util.getMsgFromErrBody
import com.example.comicfantasy.util.processNetworkError
import javax.inject.Inject

class ComicFragmentViewModel@Inject
constructor(
    private val repo: ComicRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {

    lateinit var dataResponse: DataUIModel<DataResponse>

    private val allComicUI = MutableLiveData<DataUIModel<DataResponse>>()


    fun getComic(): LiveData<DataUIModel<DataResponse>> {
       getAllComicList()
        return allComicUI
    }

    private fun getAllComicList(){
        addDisposable {
            allComicUI.postValue(DataUIModel(isLoading = true))
            repo.getComicList().subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
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

    fun getComicStory(): LiveData<DataUIModel<DataResponse>> {
        getAllComicList()
        return allComicUI
    }

    private fun getComicStory(id:Int){
        addDisposable {
            allComicUI.postValue(DataUIModel(isLoading = true))
            repo.getComicStoryApi(id).subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
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




}