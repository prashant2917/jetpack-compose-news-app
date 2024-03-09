package com.pocket.newsapp.utils.logger

import android.util.Log

class AppLogger {

    fun debug(tag:String = TAG, message :String) {
        Log.d(tag, message)
    }

    fun debug(tag:String = TAG, message :String, throwable : Throwable) {
        Log.d(tag, message, throwable)
    }

    fun error(tag:String = TAG, message :String) {
        Log.e(tag, message)
    }

    fun error(tag:String = TAG, message :String, throwable : Throwable) {
        Log.e(tag, message, throwable)
    }

    fun info(tag:String = TAG, message :String) {
        Log.i(tag, message)
    }

    fun info(tag:String = TAG, message :String, throwable : Throwable) {
        Log.i(tag, message, throwable)
    }

    fun verbose(tag:String = TAG, message :String) {
        Log.v(tag, message)
    }

    fun verbose(tag:String = TAG, message :String, throwable : Throwable) {
        Log.v(tag, message, throwable)
    }

    companion object {
        const val TAG= "News App"
    }
}