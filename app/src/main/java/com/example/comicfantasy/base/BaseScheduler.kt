package com.example.comicfantasy.base

import io.reactivex.Scheduler

interface BaseScheduler {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler


}