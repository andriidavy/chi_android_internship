package com.example.chiandroidinternship.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chiandroidinternship.R
import com.example.chiandroidinternship.data.entity.User
import com.example.chiandroidinternship.databinding.FragmentUserDetailBinding


class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        val user: User? = arguments?.getParcelable("userData")

        tvUserName.text = context?.getString(R.string.user_name, user?.name)
        tvUserAge.text = context?.getString(R.string.user_age, user?.age)
        checkBox.isChecked = user?.isStudent!!
    }

    companion object {
        fun newInstance(user: User): UserDetailFragment {
            val bundle = Bundle()
            bundle.putParcelable("userData", user)
            return UserDetailFragment().apply { arguments = bundle }
        }
    }
}