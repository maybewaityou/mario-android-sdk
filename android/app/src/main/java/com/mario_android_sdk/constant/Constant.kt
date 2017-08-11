package com.mario_android_sdk.constant

import android.annotation.SuppressLint
import android.os.Environment
import java.text.SimpleDateFormat


/**
 * Created by MeePwn on 2017/8/10.
 */

object Constant {

    /* 是否为测试环境 */
    val IS_DEBUG = true

    /* 日志 PadServer URL */
    val LOG_PAD_SERVER_URL = "http://10.240.90.212:8086/padServer/PadErrorLogController/androidErrorLog"
    /* 请求超时时间 */
    var DEFAULT_NETWORK_TIMEOUT = 1000 * 60 * 2
    /* 超时后, 是否允许重发 */
    var RETRYABLE = false
    /* 超时后, 重发次数(RETRYABLE = true时, 起作用, 默认为-1) */
    var MAX_NUM_RETRIES = 1

    /* 拍照存储路径 */
    val PHOTO_FILE_PATH = Environment.getExternalStorageDirectory().path + "/BOSPad/photos/"
    /* 图片质量 */
    val PHOTO_QUALITY = 10

    /* 本地崩溃日志路径 */
    val CRASH_REPORTER_PATH = Environment.getExternalStorageDirectory().path + "/BOSPad/"
    /* 本地崩溃日志扩展名 */
    val CRASH_REPORTER_EXTENSION = ".txt"
    /* 本地日志路径 */
    val LOG_FILE_PATH = Environment.getExternalStorageDirectory().path + "/BOSPad/logs/"
    /* 网络日志文件名 */
    val NETWORK_FILE_NAME = "network"
    /* 外设日志文件名 */
    val PERIPHERAL_FILE_NAME = "peripheral"
    /* 日志扩展名 */
    val FILE_EXTENSION = ".txt"

    /* 日志文件名称格式 */
    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("yyyy-MM-dd")
    @SuppressLint("SimpleDateFormat")
    val fmt = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

    /* 最大日志保存时间(单位: 天) */
    val MAX_LOG_FILE_SAVE_DAYS = 1


    var RESPONSE_SUCCESS = "success"
    var RESPONSE_FAILURE = "failure"

    var LOG_TAG_JOB_QUEUE = "MeePwn's Job Queue"
    var LOG_TAG_NETWORK = "MeePwn's Network"

    /* log tag */
    val LOG_TAG = "ReactNativeJS"

    /* 相机 request code */
    var CAMERA_REQUEST_CODE = 123
}
