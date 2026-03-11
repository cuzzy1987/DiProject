package com.me.feature_audio

import androidx.annotation.RequiresApi


import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.AudioPlaybackConfiguration
import android.os.Build
import android.util.Log
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AudioMonitor(
    private val context: Context,
    private val listener: OnAudioActiveListener
) {

    interface OnAudioActiveListener {
        fun onAudioActive(apps: List<AudioAppInfo>)
    }

    data class AudioAppInfo(
        val packageName: String,
        val appName: String,
        val icon: Drawable?
    )

    private val audioManager =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val executor: Executor = Executors.newSingleThreadExecutor()

//    private val playbackCallback =
//        @RequiresApi(Build.VERSION_CODES.O)
//        object : AudioManager.AudioPlaybackCallback() {
//
//            override fun onPlaybackConfigChanged(
//                configs: MutableList<AudioPlaybackConfiguration>
//            ) {
//                super.onPlaybackConfigChanged(configs)
//
//                val activeConfigs = configs.filter { config ->
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                        config.isActive
//                    } else {
//                        config.playerState ==
//                                AudioPlaybackConfiguration.PLAYER_STATE_STARTED
//                    }
//                }
//
//                val packageManager = context.packageManager
//                val result = mutableListOf<AudioAppInfo>()
//
//                activeConfigs.forEach { config ->
//                    val uid = config.clientUid
//                    val packages = packageManager.getPackagesForUid(uid)
//
//                    packages?.forEach { pkg ->
//                        try {
//                            val appInfo =
//                                packageManager.getApplicationInfo(pkg, 0)
//                            val appName =
//                                packageManager.getApplicationLabel(appInfo).toString()
//                            val icon =
//                                packageManager.getApplicationIcon(appInfo)
//
//                            result.add(
//                                AudioAppInfo(
//                                    packageName = pkg,
//                                    appName = appName,
//                                    icon = icon
//                                )
//                            )
//
//                        } catch (e: PackageManager.NameNotFoundException) {
//                            Log.e("AudioMonitor", "App not found: $pkg")
//                        }
//                    }
//                }
//
//                if (result.isNotEmpty()) {
//                    listener.onAudioActive(result)
//                }
//            }
//        }
//
//    fun start() {
//        audioManager.registerAudioPlaybackCallback(
//            playbackCallback,
//            executor
//        )
//    }
//
//    fun stop() {
//        audioManager.unregisterAudioPlaybackCallback(playbackCallback)
//    }
}
