package com.example.chiandroidinternship.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chiandroidinternship.R
import com.example.chiandroidinternship.data.entity.User
import com.example.chiandroidinternship.databinding.ActivityMainBinding
import com.example.chiandroidinternship.ui.adapter.UserAdapter
import com.example.chiandroidinternship.ui.dialog.DeleteUserDialogFragment
import com.example.chiandroidinternship.ui.dialog.SortUserDialogFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DeleteUserDialogFragment.OnDeleteUserListener,
    SortUserDialogFragment.SortingDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.actionAddUser -> {
                openAddUserFragment()
                true
            }
            R.id.actionSorting -> {
                showSortingDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        adapter = UserAdapter(emptyList(), userOnClicked(), userOnCheckBoxClicked(), userOnLongClicked())
        binding.UserRecyclerView.adapter = adapter
        binding.UserRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getAllUsers()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersListFlow.collect { resultUsersList ->
                    adapter.updateUsers(resultUsersList)
                }
            }
        }
    }

    private fun userOnClicked(): (Int) -> Unit {
        return { position ->
            val user: User = viewModel.usersListFlow.value[position]
            openDetailFragment(user)
        }
    }

    private fun userOnCheckBoxClicked(): (Int, Boolean) -> Unit {
        return { position, isChecked ->
            val user = viewModel.usersListFlow.value[position]
            viewModel.updateUser(user.copy(isStudent = isChecked))
        }
    }

    private fun userOnLongClicked(): (Int) -> Unit {
        return { position ->
            val dialog = DeleteUserDialogFragment(position)
            dialog.show(supportFragmentManager, "DeleteUserDialog")
        }
    }

    private fun openDetailFragment(user: User) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserDetailFragment.newInstance(user))
            .addToBackStack(null)
            .commit()
    }

    private fun openAddUserFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, AddUserFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDeleteUser(position: Int) {
        val user: User = viewModel.usersListFlow.value[position]
        user.let { viewModel.deleteUser(it) }
    }

    private fun showSortingDialog() {
        val dialogFragment = SortUserDialogFragment()
        dialogFragment.show(supportFragmentManager, "SortingDialogFragment")
    }

    // Implement the SortingDialogListener methods
    override fun onSortByName() {
        viewModel.sortUsersListByName()
    }

    override fun onSortByAge() {
        viewModel.sortUsersListByAge()
    }

    override fun onSortByStudentStatus() {
        viewModel.sortUsersListByStatus()
    }
}