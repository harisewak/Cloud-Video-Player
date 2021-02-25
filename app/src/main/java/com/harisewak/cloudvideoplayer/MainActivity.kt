package com.harisewak.cloudvideoplayer

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.TrendingVideos
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector.ParametersBuilder
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util
import com.harisewak.cloudvideoplayer.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //playVideo()
        Amplify.DataStore.query(
            TrendingVideos::class.java,
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    Log.i(TAG, "Queried item: " + item.id)
                    runOnUiThread { Toast.makeText(applicationContext, item.title, Toast.LENGTH_LONG).show() }
                }
            },
            {
                    failure -> Log.e(TAG, "Could not query DataStore", failure) }
        )
    }

    private fun playVideo() {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        trackSelector.setParameters(ParametersBuilder().build())
        val loadControl: LoadControl = DefaultLoadControl()
        val player = ExoPlayerFactory.newSimpleInstance(this)
        val simpleExoPlayerView = binding.exoplayer
        simpleExoPlayerView.setUseController(true)
        simpleExoPlayerView.requestFocus()
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)

        simpleExoPlayerView.setPlayer(player)
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, packageName), bandwidthMeter)
        val extractorsFactory = DefaultExtractorsFactory()
        val cfurl = "https://d1lgpxkwl1jq0h.cloudfront.net/ANNIVERSARY BHOOL GAYA शादी की सालगिरह भूल गया Hindi Family Short Movie Ruchi and Piyush.mp4"
        val s3url = "https://youtube-trending-videos.s3.ap-south-1.amazonaws.com/ANNIVERSARY+BHOOL+GAYA+%E0%A4%B6%E0%A4%BE%E0%A4%A6%E0%A5%80+%E0%A4%95%E0%A5%80+%E0%A4%B8%E0%A4%BE%E0%A4%B2%E0%A4%97%E0%A4%BF%E0%A4%B0%E0%A4%B9+%E0%A4%AD%E0%A5%82%E0%A4%B2+%E0%A4%97%E0%A4%AF%E0%A4%BE+Hindi+Family+Short+Movie+Ruchi+and+Piyush.mp4"
        val mediaSource = ExtractorMediaSource(Uri.parse(s3url), dataSourceFactory, extractorsFactory, null, null)
        player.prepare(mediaSource)
        player.setPlayWhenReady(true)
    }
}