package com.harisewak.cloudvideoplayer.util

import com.amplifyframework.datastore.generated.model.TrendingVideos
import com.harisewak.cloudvideoplayer.video.VideoModel

object DataTransformUtil {

    fun TrendingVideos.toVideoModel (): VideoModel {
        return VideoModel(this.id, this.title, this.s3Url, this.cfUrl)
    }
}