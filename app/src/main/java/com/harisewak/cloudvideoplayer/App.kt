package com.harisewak.cloudvideoplayer

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "App"

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)
            Log.i(TAG, "Initialized Amplify")
        } catch (failure: AmplifyException) {
            Log.e(TAG, "Could not initialize Amplify", failure)
        }
    }
}