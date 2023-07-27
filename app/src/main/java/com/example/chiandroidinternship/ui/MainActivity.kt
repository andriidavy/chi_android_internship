package com.example.chiandroidinternship.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.example.chiandroidinternship.R
import com.example.chiandroidinternship.databinding.ActivityMainBinding
import com.example.chiandroidinternship.viewmodel.CounterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setListeners()
    }

    private fun setObservers()  {
        viewModel.counter.observe(this) { result ->
            binding.tvCountResult.text = result.toString()
        }
    }

    private fun setListeners() = with(binding) {
        btOpenFragment.setOnClickListener {
            openFragment()
        }
    }

    private fun openFragment() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, CounterFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}