package com.mario_android_sdk.vendors.log

import com.mario_android_sdk.constant.Constant
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Created by MeePwn on 2017/8/10.
 */

object JLog {

    val DEBUG = 3
    val ERROR = 6
    val ASSERT = 7
    val INFO = 4
    val VERBOSE = 2
    val WARN = 5

    fun init() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .tag(Constant.LOG_TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    fun log(priority: Int, tag: String, message: String, throwable: Throwable) {
        if (Constant.IS_DEBUG) Logger.log(priority, tag, message, throwable)
    }

    fun d(message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.d(message, *args)
    }

    fun d(`object`: Any) {
        if (Constant.IS_DEBUG) Logger.d(`object`)
    }

    fun e(message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.e(Throwable(), message, *args)
    }

    fun e(throwable: Throwable, message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.i(message, *args)
    }

    fun v(message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.v(message, *args)
    }

    fun w(message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.w(message, *args)
    }

    fun wtf(message: String, vararg args: Any) {
        if (Constant.IS_DEBUG) Logger.wtf(message, *args)
    }

    /**
     * Formats the json content and print it

     * @param json the json content
     */
    fun json(json: String) {
        if (Constant.IS_DEBUG) Logger.json(json)
    }

    /**
     * Formats the json content and print it

     * @param xml the xml content
     */
    fun xml(xml: String) {
        if (Constant.IS_DEBUG) Logger.xml(xml)
    }

}
