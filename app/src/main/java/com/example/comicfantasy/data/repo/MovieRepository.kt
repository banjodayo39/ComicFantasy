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


    fun getPopularMovieList(): Observable<List<MovieResult?>> =
        Observable.concat(getPopularMovieFromDb(), getPopularMovieFromApi())
            .onErrorResumeNext(Observable.empty())

    fun getTopRatedMovieList(): Observable<List<MovieResult?>> =
        Observable.concat(getTopRatedMovieFromDb(), getTopRatedMovieFromApi())
            .onErrorResumeNext(Observable.empty())

    fun getNowPlayingMovieList(): Observable<List<MovieResult?>> =
        Observable.concat(getNowPlayingMovieFromApi(), getNowPlayingMovieFromDb())
            .onErrorResumeNext(Observable.empty())

    fun getUpcomingMovieList(): Observable<List<MovieResult?>> =
        Observable.concat(getUpcomingMovieFromDb(),getUpComingMovieFromApi())
            .onErrorResumeNext(Observable.empty())

    fun getLatestMovieList(): Observable<List<MovieResult?>> =
        Observable.concat(getLatestMovieFromDb(), getLatestMovieFromApi())
            .onErrorResumeNext(Observable.empty())


    private fun getPopularMovieFromDb(): Observable<List<MovieResult>> =
        Observable.fromCallable { movieDao.getAllPopularMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getTopRatedMovieFromDb(): Observable<List<MovieResult>> =
        Observable.fromCallable { movieDao.getAllTopRatedMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getNowPlayingMovieFromDb(): Observable<List<MovieResult>> =
        Observable.fromCallable { movieDao.getNowPlaying() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getUpcomingMovieFromDb(): Observable<List<MovieResult>> =
        Observable.fromCallable { movieDao.getUpcomingMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getLatestMovieFromDb(): Observable<List<MovieResult>> =
        Observable.fromCallable { movieDao.getLatestMovie() }
            .filter { !it.isNullOrEmpty() }
            .subscribeOn(provider.io())

    private fun getPopularMovieFromApi(): Observable<List<MovieResult?>> =
        apiService.getMovie(MOVIE_BASE_URL, apikey)
            .subscribeOn(provider.io())
            .doOnNext {
                if (it.isSuccessful && it.body() != null)
                    savePopularMovie(it.body()!!.list as List<MovieResult>)
            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getTopRatedMovieFromApi(): Observable<List<MovieResult?>> =
        apiService.getTopRatedMovie(TOP_RATED_BASE_URL, apikey)
            .subscribeOn(provider.io())
            .doOnNext {
                if (it.isSuccessful && it.body() != null)
                    saveTopRatedMovie(it.body()!!.list as List<MovieResult>)
            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getNowPlayingMovieFromApi(): Observable<List<MovieResult?>> =
        apiService.getNowPlayingMovie(NOW_PLAYING_URL, apikey)
            .subscribeOn(provider.io())
            .doOnNext {
                if (it.isSuccessful && it.body() != null)
                    saveNowPlayingMovie(it.body()!!.list as List<MovieResult>)
            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getUpComingMovieFromApi(): Observable<List<MovieResult?>> =
        apiService.getUpComingMovie(UPCOMING_BASE_URL, apikey)
            .subscribeOn(provider.io())
            .doOnNext {
                if (it.isSuccessful && it.body() != null)
                    saveUpcomingMovie(it.body()!!.list as List<MovieResult>)
            }
            .map {
                it.body()!!.list!!.toList()
            }

    private fun getLatestMovieFromApi(): Observable<List<MovieResult?>> =
        apiService.getLatestMovie(LATEST_BASE_URL, apikey)
            .subscribeOn(provider.io())
            .doOnNext {
                if (it.isSuccessful && it.body() != null)
                    saveLatestMovie(it.body()!!.list as List<MovieResult>)
            }
            .map {
                it.body()!!.list!!.toList()
            }



    fun getMovieTrailerFromApi(id: Int): Observable<Trailer> =
        apiService.getTrailers("http://api.themoviedb.org/3/movie/$id/videos", apikey)
            .subscribeOn(provider.io()).map { it.body() }


    fun savePopularMovie(movie: List<MovieResult>) {
        Completable.fromAction {
            movieDao.addPopularMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveTopRatedMovie(movie: List<MovieResult>) {
        Completable.fromAction {
            movieDao.addTopRatedMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveNowPlayingMovie(movie: List<MovieResult>) {
        Completable.fromAction {
            movieDao.addNowPlayingMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveUpcomingMovie(movie: List<MovieResult>) {
        Completable.fromAction {
            movieDao.addUpcomingMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }

    fun saveLatestMovie(movie: List<MovieResult>) {
        Completable.fromAction {
            movieDao.addLatestMovie(movie)
        }.subscribeOn(provider.io())
            .subscribe()
    }
}
