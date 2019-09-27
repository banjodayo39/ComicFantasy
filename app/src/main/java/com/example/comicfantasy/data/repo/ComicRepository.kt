package com.example.comicfantasy.data.repo

import com.example.comicfantasy.data.local.ComicDAO
import com.example.comicfantasy.data.remote.ComicApiService
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.util.SchedulerProvider
import io.reactivex.Observable

open class ComicRepository (
    private val apiService: ComicApiService,
    private val comicDao: ComicDAO,
    private val provider: SchedulerProvider
) {


    private val ts: String = "1"
    private val apikey: String = "610dae96da266869750c65574438abb0"
    private val hash: String = "31425e31bec201f47f25948808f8f341"


    fun getComicList(): Observable<DataResponse> =
   Observable.concat(getComicListFromDb(), getComicListFromApi())
            .onErrorResumeNext(Observable.empty())

    private fun saveComic(comic: DataResponse) =
        comicDao.addComic(comic)

    private fun getComicListFromDb(): Observable<DataResponse> =
        Observable.fromCallable { comicDao.getAllComics() }
            .filter { it!=null }
            .subscribeOn(provider.io())

    private fun getComicListFromApi(): Observable<DataResponse> =
        apiService.fetchListOfComic(ts, apikey, hash)
            .subscribeOn(provider.io())
            .doOnNext {
                if(it.isSuccessful && it.body()!=null)
                    saveComic(it.body()!!)
            }
            .map {
                it.body()
            }

   fun getComicStoryApi(): Observable<DataResponse> =
        apiService.fetchComicStory(ts, apikey, hash)
            .subscribeOn(provider.io())
            .doOnNext {
                if(it.isSuccessful && it.body()!=null)
                    saveComic(it.body()!!)
            }
            .map {
                it.body()
            }
}