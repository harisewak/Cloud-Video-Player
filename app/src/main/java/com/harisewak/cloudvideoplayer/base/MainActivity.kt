package com.harisewak.cloudvideoplayer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.datastore.generated.model.TrendingVideos
import com.harisewak.cloudvideoplayer.R
import com.harisewak.cloudvideoplayer.databinding.ActivityMainBinding
import com.harisewak.cloudvideoplayer.list.ListFragment
import com.harisewak.cloudvideoplayer.util.DataTransformUtil.toVideoModel
import com.harisewak.cloudvideoplayer.video.VideoFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //playVideo()
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListFragment.newInstance())
            .commit()
    }

    public fun playVideo(item: TrendingVideos) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, VideoFragment.newInstance(item.toVideoModel()))
            .commit()
    }
}