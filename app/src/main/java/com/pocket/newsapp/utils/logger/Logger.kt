package com.pocket.newsapp.utils.logger

interface Logger {
    fun debug(tag: String = AppLogger.TAG, message: String)
    fun debug(tag: String = AppLogger.TAG, message: String, throwable: Throwable)
    fun error(tag: String = AppLogger.TAG, message: String)
    fun error(tag: String = AppLogger.TAG, message: String, throwable: Throwable)
    fun info(tag: String = AppLogger.TAG, message: String)
    fun info(tag: String = AppLogger.TAG, message: String, throwable: Throwable)
    fun verbose(tag: String = AppLogger.TAG, message: String)
    fun verbose(tag: String = AppLogger.TAG, message: String, throwable: Throwable)
}