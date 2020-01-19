package com.example.eBox.data.repo

import com.example.eBox.data.local.GamesDAO
import com.example.eBox.data.remote.*
import com.example.eBox.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable

open class GamesRepository(
    private val apiService:GamesApiService,
    private val gamesDao: GamesDAO,
    private val provider : SchedulerProvider
) {


    private val url: String = "https://opentdb.com/api.php?amount=10"
    fun getTrivia(): Observable<List<GamesResult>> =
    Observable.concat(getGamesFromDb(), getGamesListFromApi())
          .onErrorResumeNext(Observable.empty())


    /*   private fun saveComic(comic: DataResponse) =
           comicDao.addComic(comic)*/

    private fun getGamesFromDb(): Observable<List<GamesResult>> =
        Observable.fromCallable { gamesDao.getAllTrivia()}
            .filter { it!=null }
            .subscribeOn(provider.io())

    private fun getGamesListFromApi(): Observable<List<GamesResult>> =

        apiService.getTrivia(url)
            .subscribeOn(provider.io())
            .doOnNext {
                if(it.isSuccessful && it.body()!=null)
                    saveTrivia(it.body()!!.results as ArrayList<GamesResult>)
            }
            .map {
                it.body()!!.results!!.toList()
            }


    private fun saveTrivia(trivia: List<GamesResult>) {
        Completable.fromAction {
            gamesDao.addtrivia(trivia)
        }.subscribeOn(provider.io())
            .subscribe()
    }
}