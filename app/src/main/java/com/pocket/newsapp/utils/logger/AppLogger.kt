package com.pocket.newsapp.utils.logger

import android.util.Log

class AppLogger: Logger {

    override fun debug(tag:String, message :String) {
        Log.d(tag, message)
    }

    override fun debug(tag:String, message :String, throwable : Throwable) {
        Log.d(tag, message, throwable)
    }

    override fun error(tag:String, message :String) {
        Log.e(tag, message)
    }

    override fun error(tag:String, message :String, throwable : Throwable) {
        Log.e(tag, message, throwable)
    }

    override fun info(tag:String, message :String) {
        Log.i(tag, message)
    }

    override fun info(tag:String, message :String, throwable : Throwable) {
        Log.i(tag, message, throwable)
    }

    override fun verbose(tag:String, message :String) {
        Log.v(tag, message)
    }

    override fun verbose(tag:String, message :String, throwable : Throwable) {
        Log.v(tag, message, throwable)
    }

    companion object {
        const val TAG= "News App"
    }
}