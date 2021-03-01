package com.harisewak.cloudvideoplayer.video

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amplifyframework.datastore.generated.model.TrendingVideos
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.LoadControl
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.harisewak.cloudvideoplayer.databinding.FragmentListBinding
import com.harisewak.cloudvideoplayer.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {
    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(item: VideoModel): VideoFragment {
            val args = Bundle()
            args.putParcelable("item", item)
            val fragment = VideoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadVideo()
    }

    fun loadVideo() {
        val video = arguments?.get("item") as VideoModel
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory =
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        trackSelector.parameters = DefaultTrackSelector.ParametersBuilder().build()
        val player = ExoPlayerFactory.newSimpleInstance(context)
        val simpleExoPlayerView = binding.playerView
        simpleExoPlayerView.setUseController(true)
        simpleExoPlayerView.requestFocus()
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)

        simpleExoPlayerView.setPlayer(player)
        val dataSourceFactory =
            DefaultDataSourceFactory(context, Util.getUserAgent(context, context?.packageName))
        val extractorsFactory = DefaultExtractorsFactory()
                val mediaSource =
            ExtractorMediaSource(Uri.parse(video.cfUrl), dataSourceFactory, extractorsFactory, null, null)
        player.prepare(mediaSource)
        player.setPlayWhenReady(true)
    }
}