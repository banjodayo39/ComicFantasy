package com.example.comicfantasy.data.repo

import android.util.Log
import com.example.comicfantasy.data.local.ComicDAO
import com.example.comicfantasy.data.remote.ComicApiService
import com.example.comicfantasy.data.remote.ComicResults
import com.example.comicfantasy.util.PaginatedResp
import com.example.comicfantasy.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable

open class ComicRepository(
    private val apiService: ComicApiService,
    private val comicDao: ComicDAO,
    private val provider: SchedulerProvider
) {


    private val ts: String = "1"
    private val apikey: String = "610dae96da266869750c65574438abb0"
    private val hash: String = "31425e31bec201f47f25948808f8f341"


    fun getComicList(): Observable<List<ComicResults?>> =
        Observable.concat(getComicListFromDb(), getComicListApi())
            .onErrorResumeNext(Observable.empty())


    /*   private fun saveComic(comic: DataResponse) =
           comicDao.addComic(comic)*/

    /* private fun getComicListFromDb(): Observable<DataX>? =
         Observable.fromCallable { comicDao.getAllComics() }
             .filter { it !=  null }
             .subscribeOn(provider.io())

     private fun getComicListFromApi(): Observable<DataX?>? =
         apiService.fetchListOfComic(ts, apikey, hash)
             .subscribeOn(provider.io())
             .doOnNext {
                 if(it.isSuccessful && it.body()!=null)
                     saveComic(it.body()!!)
             }
             .map {
                 it.body()
             }*/

    fun getComicListFromDb(): Observable<List<ComicResults>> =
        Observable.fromCallable { comicDao.getAllComics() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    fun getComicListApi(): Observable<List<ComicResults?>> =
        apiService.fetchListOfComic(ts, apikey, hash)
            .subscribeOn(provider.io())
            .doOnNext {
                Log.e("data", "is not null")
                if (it.isSuccessful && it.body()!!.list != null)
                    saveComic(it.body()!!.list as List<ComicResults>)
            }
            .map {
                it.body()!!.list as List<ComicResults>
            }


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