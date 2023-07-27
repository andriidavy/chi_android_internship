package com.example.chiandroidinternship.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.chiandroidinternship.databinding.FragmentCounterBinding
import com.example.chiandroidinternship.viewmodel.CounterViewModel

class CounterFragment : Fragment() {

    private lateinit var binding: FragmentCounterBinding
    private val viewModel by activityViewModels<CounterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setToolbar()
        setListeners()
    }

    private fun setToolbar() = with(binding) {
        (activity as AppCompatActivity).apply {
            // set toolbar as action bar
            setSupportActionBar(fragmentToolbar)

            // show button "back" in toolbar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setListeners() = with(binding) {
        btCounter.setOnClickListener {
            viewModel.increaseCounter()
        }

        fragmentToolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setObservers() = with(binding) {
        viewModel.counter.observe(viewLifecycleOwner) { result ->
            tvClickCount.text = result.toString()
        }
    }
}