package com.example.chiandroidinternship.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chiandroidinternship.databinding.FragmentMainBinding
import com.example.chiandroidinternship.ui.adapter.ImageListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ImageListAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setObservers()
    }

    private fun setupViews() = with(binding) {
        val gridLayoutManager = GridLayoutManager(context, 2)
        adapter = ImageListAdapter(emptyList()) { position -> viewModel.changeFavourite(position) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setOnScrollChangeListener { view, _, _, _, _ ->
            if (!view.canScrollVertically(1)) {
                viewModel.getNextShibes()
            }
        }
    }

    private fun setObservers() {
        viewModel.shibes.observe(viewLifecycleOwner) { shibes ->
            adapter.updateShibesList(shibes)
        }
    }
}