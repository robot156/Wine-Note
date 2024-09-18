package com.winenote.util.initialize

import android.content.Context
import androidx.startup.Initializer
import com.winenote.util.CrashlyticsMapper
import timber.log.Timber
import com.winenote.BuildConfig

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsMapper())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}