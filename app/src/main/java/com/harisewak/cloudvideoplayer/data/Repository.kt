package com.harisewak.cloudvideoplayer.data

import android.util.Log
import com.amplifyframework.core.Action
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.Consumer
import com.amplifyframework.core.async.Cancelable
import com.amplifyframework.core.model.Model
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.DataStoreItemChange
import com.amplifyframework.datastore.generated.model.TrendingVideos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable.just
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "Repository"

class Repository @Inject constructor() {

    fun getTrendingVideos(callback: Callback) {
        Amplify.DataStore.observe(
            { Log.d(TAG, "onObservationStarted") }, {
                Log.d(TAG, "onDataStoreChange received")
                Amplify.DataStore.query(
                    TrendingVideos::class.java,
                    { items ->
                        callback.success(items.asFlow())
                    },
                    { failure -> callback.failure(failure) }
                )
            },
            { Log.d(TAG, "onObservationFailure") }, { Log.d(TAG, "onObservationCompleted") })
    }


    interface Callback {
        fun success(videos: Flow<TrendingVideos>)
        fun failure(exception: Exception)
    }
}