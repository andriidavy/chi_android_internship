package com.example.chiandroidinternship.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chiandroidinternship.R
import com.example.chiandroidinternship.data.entity.User
import com.example.chiandroidinternship.databinding.ActivityMainBinding
import com.example.chiandroidinternship.ui.adapter.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        setObservers()
        setListeners()
    }

    private fun initAdapter() {
        adapter = UserAdapter(emptyList())
        binding.UserRecyclerView.adapter = adapter
        binding.UserRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        viewModel.getAllUsers().observe(this) { resultUsersList ->
            adapter.updateUsers(resultUsersList)
        }
    }

    private fun setListeners() {
        adapter.apply {
            setOnItemClickListener(object : UserAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val user: User? = viewModel.usersListLiveData.value?.getOrNull(position)
                    user?.let { openFragment(it) }
                }
            })

            setOnCheckBoxClickListener(object : UserAdapter.OnCheckBoxClickListener {
                override fun onCheckBoxClick(position: Int, isChecked: Boolean) {
                    val user: User? = viewModel.usersListLiveData.value?.get(position)
                    user?.isStudent = isChecked
                    user?.let { viewModel.updateUser(it) }
                }
            })
        }
    }

    private fun openFragment(user: User) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserDetailFragment.newInstance(user))
            .addToBackStack(null)
            .commit()
    }
}