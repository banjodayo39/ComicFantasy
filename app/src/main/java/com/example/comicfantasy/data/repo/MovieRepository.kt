package com.example.comicfantasy.data.repo


import androidx.lifecycle.LiveData
import com.example.comicfantasy.data.local.MovieDAO
import com.example.comicfantasy.data.remote.DataResponse
import com.example.comicfantasy.data.remote.MovieApiService
import com.example.comicfantasy.data.remote.MovieDataResponse
import com.example.comicfantasy.data.remote.Trailer
import com.example.comicfantasy.util.DataResp
import com.example.comicfantasy.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

open class MovieRepository (
    private val apiService: MovieApiService,
    private val movieDao: MovieDAO,
    private val provider :SchedulerProvider
    ) {

    //https://api.themoviedb.org/3/movie/475557/videos?api_key=a0b45b8501266c1f4a40ac53d4faaedc
   // http://api.themoviedb.org/3/movie/550?api_key=a0b45b8501266c1f4a40ac53d4faaedc
//("movie/{id}/videos")
    private val apikey: String = "a0b45b8501266c1f4a40ac53d4faaedc"
    private val MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie/popular"
    private val VIDEO_URL="http://api.themoviedb.org/3/movie/{id}"


    fun getMovieList(): Observable<MovieDataResponse> = getComicMovieFromApi()
       /*  Observable.concat(getComicMovieFromDb(), getComicMovieFromApi())
                .onErrorResumeNext(Observable.empty())*/

     /* private fun saveMovie(movie: MovieDataResponse) =
           movieDao.addMovie(movie)
*/
       private fun getComicMovieFromDb(): Observable<MovieDataResponse> =
            Observable.fromCallable { movieDao.getAllMovie() }
                .filter { it!=null }
                .subscribeOn(provider.io())

        private fun getComicMovieFromApi(): Observable<MovieDataResponse> =
            apiService.getPopularMovie(MOVIE_BASE_URL,apikey)
                .subscribeOn(provider.io())
                .doOnNext {
                    if(it.isSuccessful && it.body()!=null)
                       saveMovie(it.body()!!)
                }
                .map {
                    it.body()
                }


     fun getMovieTrailerFromApi(id:Int): Observable<Trailer> =
        apiService.getTrailers("http://api.themoviedb.org/3/movie/$id/videos",apikey)
            .subscribeOn(provider.io()).map { it.body() }



    /*private fun getTrailerFromApi(): Observable<TrailerResponse> =
        apiService.getTrailers(MOVIE_BASE_URL,id,apikey)
            .subscribeOn(provider.io())
            .doOnNext {
                if(it.isSuccessful && it.body()!=null)
                    saveMovie(it.body()!!)
            }
            .map {
                it.body()
            }
    */


/*

    fun getProgramSubscription(programId: Int): Observable<MovieDataResponse> =
        movieDao .getRoomUser()
            .flatMap {
                programAPIService.getProgramSubscription(it.userId!!, programId)
            }
            .subscribeOn(provider.io())
            .doOnSuccess {
                if (it.body()?.data != null)
                    saveProgramSubscription(it.body()?.data!!)
            }

    fun getProgramSubscriptionFromDb(programId: Int): LiveData<ProgramSubscription> =
        programDAO.getProgramSubscriptionByProgramId(programId)
*/


    fun saveMovie(movie: MovieDataResponse) {
        Completable.fromAction {
            movieDao.addMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }


    }
