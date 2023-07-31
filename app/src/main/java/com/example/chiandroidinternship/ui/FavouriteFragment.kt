package com.example.chiandroidinternship.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chiandroidinternship.databinding.FragmentFavouriteBinding
import com.example.chiandroidinternship.ui.adapter.ImageListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var adapter: ImageListAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribe()
    }

    private fun setupViews() = with(binding) {
        val gridLayoutManager = GridLayoutManager(context, 2)
        adapter = ImageListAdapter(emptyList()) { position ->
            viewModel.deleteFavourite(viewModel.favouriteShibes.value[position].id)
        }
        favouriteRecyclerView.adapter = adapter
        favouriteRecyclerView.layoutManager = gridLayoutManager
    }

    private fun subscribe() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouriteShibes.collect {
                    adapter.updateShibesList(it)
                }
            }
        }
    }
}