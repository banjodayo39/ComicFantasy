package com.example.eBox.data.repo

import android.util.Log
import com.example.eBox.data.local.ComicDAO
import com.example.eBox.data.remote.ComicApiService
import com.example.eBox.data.remote.ComicResults
import com.example.eBox.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable

open class ComicRepository(
    private val apiService: ComicApiService,
    private val comicDao: ComicDAO,
    private val provider: SchedulerProvider
) {

    private val COMIC_BASE_URL = "http://gateway.marvel.com/v1/public/comics?"
    private val ts: String = "1"
    private val apikey: String = "610dae96da266869750c65574438abb0"
    private val hash: String = "31425e31bec201f47f25948808f8f341"


    fun getComicList(): Observable<List<ComicResults>> =
        Observable.concat(getComicListFromDb(),getComicListApi())
            .onErrorResumeNext(Observable.empty())

    fun getComicListFromDb(): Observable<List<ComicResults>> =
        Observable.fromCallable { comicDao.getAllComics()
       }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())
            .observeOn(provider.io())

    fun getComicListApi(): Observable<List<ComicResults>> =
        apiService.fetchListOfComic(COMIC_BASE_URL, ts, apikey, hash)
            .subscribeOn(provider.io())
            .observeOn(provider.io())
            .doOnNext {
                Log.e("data", "is not null")
                if (it.isSuccessful && it.body()!!.data != null)
                    saveComic(it.body()!!.data!!.results as List<ComicResults>)
            }
            .map {
                it.body()!!.data!!.results as List<ComicResults>
            }.subscribeOn(provider.io())


    /*.subscribeOn(provider.io())
    .doOnSuccess {
        if (it.body()?.data != null)
            saveProgramSubscription(it.body()?.data!!)
    }*/


    fun saveComic(comic: List<ComicResults>) {
        Completable.fromAction {
            comicDao.addComic(comic)
        }.subscribeOn(provider.io())
            .subscribe()
    }
}