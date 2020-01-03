package com.example.comicfantasy.comic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comicfantasy.base.BaseViewModel
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.remote.DataX
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.data.repo.ComicRepository
import com.example.comicfantasy.util.*
import java.util.*
import javax.inject.Inject

class ComicFragmentViewModel@Inject
constructor(
    private val repo: ComicRepository,
    private val provider: SchedulerProvider
) : BaseViewModel() {

    lateinit var dataResponse: DataUIModel<DataResponse>

    private val allComicUI = MutableLiveData<ListUIModel<Results?>>()

    fun updateComicList() {
        getAllComicList()
    }

    fun getComic(): LiveData<ListUIModel<Results?>> {
       getAllComicList()
        return allComicUI
    }

    private fun getAllComicList(){
        addDisposable {
            allComicUI.postValue(ListUIModel(isLoading = true))
            repo.getComicList()!!.subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                if(!it.isNullOrEmpty()) {
                    allComicUI.postValue(ListUIModel(list = it ))
                    repo.saveComic(it as List<Results>)
                }
                else
                    allComicUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
               allComicUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!


        }
    }

   /* fun getAllTheComicList(): Observable<List<Results?>> =
        repo.getComicListFromDb()
*/

/*    fun getComicStory(): LiveData<ListUIModel<Results>> {
        getAllComicList()
        return allComicUI
    }

    private fun getComicStory(id:Int){
        addDisposable {
            allComicUI.postValue(ListUIModel(isLoading = true))
            repo.getComicStoryApi(id).subscribeOn(provider.io())?.observeOn(provider.ui())?.subscribe({
                Log.e("ComicFragmentViewModel",it.toString())
                if(it != null)
                    allComicUI.postValue(ListUIModel(list = it))
                else
                    allComicUI.postValue(ListUIModel(error = getMsgFromErrBody("error_here")))
            }, {
                allComicUI.postValue(ListUIModel(error = processNetworkError(it)))
            })!!

        }*/
    }
