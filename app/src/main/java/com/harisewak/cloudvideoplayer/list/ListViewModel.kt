package com.harisewak.cloudvideoplayer.list

import androidx.lifecycle.ViewModel
import com.harisewak.cloudvideoplayer.data.Repository
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getTrendingVideos(callback: Repository.Callback) {
       repository.getTrendingVideos(callback)
    }

}