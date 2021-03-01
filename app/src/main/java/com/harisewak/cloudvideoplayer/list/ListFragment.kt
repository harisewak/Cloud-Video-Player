package com.harisewak.cloudvideoplayer.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.TrendingVideos
import com.harisewak.cloudvideoplayer.R
import com.harisewak.cloudvideoplayer.base.MainActivity
import com.harisewak.cloudvideoplayer.data.Repository
import com.harisewak.cloudvideoplayer.data.Repository.Callback
import com.harisewak.cloudvideoplayer.databinding.FragmentListBinding
import com.harisewak.cloudvideoplayer.databinding.ItemListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment @Inject constructor() : Fragment(), ItemClick {
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    @Inject lateinit var viewModel: ListViewModel

    companion object {
        fun newInstance(): ListFragment {
            val args = Bundle()
            val fragment = ListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        viewModel.getTrendingVideos(object : Callback {

            override fun failure(exception: Exception) {
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            }

            override fun success(videos: Flow<TrendingVideos>) {
                val list = ArrayList<TrendingVideos>()
                lifecycleScope.launch {
                    videos.collectLatest {
                        list.add(it)
                    }
                    binding.rvList.adapter = TrendingVideosListAdapter(list, this@ListFragment)
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    class TrendingVideosListAdapter(val list: List<TrendingVideos>, val click: ItemClick) :
        RecyclerView.Adapter<TrendingVideosViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TrendingVideosViewHolder {
            val itemBinding = ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TrendingVideosViewHolder(itemBinding, click)
        }

        override fun onBindViewHolder(holder: TrendingVideosViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }


    }

    class TrendingVideosViewHolder(private val binding: ItemListBinding, private val click: ItemClick) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(item: TrendingVideos) {
            binding.tvTitle.text = item.title
            // TODO: 001 01 Mar 21 bind thumbnail later
            binding.root.setOnClickListener { click.click(item) }
        }


    }

    override fun click(item: TrendingVideos) {
        (activity as MainActivity).playVideo(item)
    }

}

interface ItemClick {
    fun click(item: TrendingVideos)
}
