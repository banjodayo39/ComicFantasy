package com.example.comicfantasy.data.repo


import com.example.comicfantasy.data.local.MovieDAO
import com.example.comicfantasy.data.remote.*
import com.example.comicfantasy.util.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable

open class MovieRepository(
    private val apiService: MovieApiService,
    private val movieDao: MovieDAO,
    private val provider: SchedulerProvider
) {

    //https://api.themoviedb.org/3/movie/475557/videos?api_key=a0b45b8501266c1f4a40ac53d4faaedc
    // http://api.themoviedb.org/3/movie/550?api_key=a0b45b8501266c1f4a40ac53d4faaedc
//("movie/{id}/videos")
    private val apikey: String = "a0b45b8501266c1f4a40ac53d4faaedc"
    private val MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie/popular"
    private val TOP_RATED_BASE_URL = "http://api.themoviedb.org/3/movie/top_rated"
    private val UPCOMING_BASE_URL = "https://api.themoviedb.org/3/movie/upcoming"
    private val LATEST_BASE_URL = "http://api.themoviedb.org/3/movie/latest"
    private val NOW_PLAYING_URL = "http://api.themoviedb.org/3/movie/now_playing"
    private val VIDEO_URL = "http://api.themoviedb.org/3/movie/{id}"


    fun getPopularMovieList(): Observable<List<PopularMovie?>> =
        Observable.concat(getPopularMovieFromDb(), getPopularMovieFromApi())
            .onErrorResumeNext(Observable.empty())

    fun getTopRatedMovieList(): Observable<List<TopRatedMovie?>> =
        Observable.concat(getTopRatedMovieFromDb(), getTopRatedMovieFromApi())
            .onErrorResumeNext(Observable.empty())

    fun getNowPlayingMovieList(): Observable<List<NowShowing?>> =
        Observable.concat(getNowPlayingMovieFromApi(), getNowPlayingMovieFromDb())
            .onErrorResumeNext(Observable.empty())

    fun getUpcomingMovieList(): Observable<List<UpComing?>> =
        Observable.concat(getUpcomingMovieFromDb(),getUpComingMovieFromApi())
            .onErrorResumeNext(Observable.empty())

    fun getLatestMovieList(): Observable<Latest?> =
        Observable.concat(getLatestMovieFromDb(), getLatestMovieFromApi())
            .onErrorResumeNext(Observable.empty())


    private fun getPopularMovieFromDb(): Observable<List<PopularMovie>> =
        Observable.fromCallable { movieDao.getAllPopularMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getTopRatedMovieFromDb(): Observable<List<TopRatedMovie>> =
        Observable.fromCallable { movieDao.getAllTopRatedMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getNowPlayingMovieFromDb(): Observable<List<NowShowing>> =
        Observable.fromCallable { movieDao.getNowPlaying() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getUpcomingMovieFromDb(): Observable<List<UpComing>> =
        Observable.fromCallable { movieDao.getUpcomingMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getLatestMovieFromDb(): Observable<Latest> =
        Observable.fromCallable { movieDao.getLatestMovie() }
            .subscribeOn(provider.io())

    private fun getPopularMovieFromApi(): Observable<List<PopularMovie?>> =
        apiService.getMovie(apikey)
            .subscribeOn(provider.io())
            .doOnNext { it1 ->
                if (it1.isSuccessful && it1.body() != null)
                {
                    it1.body()!!.list!!.map {
                        savePopularMovie(it)
                    } }

            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getTopRatedMovieFromApi(): Observable<List<TopRatedMovie?>> =
        apiService.getTopRatedMovie( apikey)
            .subscribeOn(provider.io())
            .doOnNext { it1 ->
                if (it1.isSuccessful && it1.body() != null)
                {
                        saveTopRatedMovie(it1.body()!!.list!!)
                }

            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getNowPlayingMovieFromApi(): Observable<List<NowShowing?>> =
        apiService.getNowPlayingMovie( apikey)
            .subscribeOn(provider.io())
            .doOnNext { it1 ->
                if (it1.isSuccessful && it1.body() != null){
                    it1.body()!!.list!!.map {
                        saveNowPlayingMovie(it)
                    }
                }
            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getUpComingMovieFromApi(): Observable<List<UpComing?>> =
        apiService.getUpComingMovie( apikey)
            .subscribeOn(provider.io())
            .doOnNext { it1 ->
                if (it1.isSuccessful && it1.body() != null) {
                   it1.body()!!.list!!.map {
                       saveUpcomingMovie(it)
                   }
                }
            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getLatestMovieFromApi(): Observable<Latest?> =
        apiService.getLatestMovie( apikey)
            .subscribeOn(provider.io())
            .doOnNext { it1 ->
                if (it1.isSuccessful && it1.body() != null) {
                       saveLatestMovie(it1.body()!! )
                }
            }
            .map {
                it.body()!!
            }



    fun getMovieTrailerFromApi(id: Int): Observable<Trailer> =
        apiService.getTrailers(id, apikey)
            .subscribeOn(provider.io()).map { it.body() }

  /*  fun getMovieTrailerFromApi(id: Int): Observable<Trailer> =
        apiService.getTrailers("http://api.themoviedb.org/3/movie/$id/videos", apikey)
            .subscribeOn(provider.io()).map { it.body() }
*/

    fun savePopularMovie(movie: PopularMovie) {
        Completable.fromAction {
            movieDao.addPopularMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveTopRatedMovie(movie: List<TopRatedMovie>) {
        Completable.fromAction {
            movieDao.addTopRatedMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveNowPlayingMovie(movie: NowShowing) {
        Completable.fromAction {
            movieDao.addNowPlayingMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveUpcomingMovie(movie: UpComing) {
        Completable.fromAction {
            movieDao.addUpcomingMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveLatestMovie(movie: Latest) {
        Completable.fromAction {
            movieDao.addLatestMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }
}
