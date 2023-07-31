package com.example.chiandroidinternship.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.chiandroidinternship.databinding.FragmentAddUserBinding
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*


class AddUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() = with(binding) {
        dpBirthday.maxDate = System.currentTimeMillis()
        dpBirthday.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            val selectedDate= getTimeInMillis(year, monthOfYear, dayOfMonth)
            etUserAge.setText(getAge(selectedDate).toString())
        }

        etUserAge.setText(
            getAge(
                getTimeInMillis(
                    dpBirthday.year,
                    dpBirthday.month,
                    dpBirthday.dayOfMonth
                )
            ).toString()
        )

        btAddUser.setOnClickListener {
            val name: String = etUserName.text.toString().trim()
            val age: String = etUserAge.text.toString()
            val dateOfBirth = getFormattedDateTime(dpBirthday.year, dpBirthday.month, dpBirthday.dayOfMonth)
            if (name.isNotBlank() && age.isNotBlank() && dateOfBirth.isNotBlank()) {
                viewModel.storeUser(name, age, dateOfBirth)
                closeFragment()
                toastMessage("User added successfully!")
            } else {
                toastMessage("Incorrect values!")
            }
        }
    }

    private fun getTimeInMillis(year: Int, month: Int, day: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return calendar.timeInMillis
    }

    private fun getAge(instant: Long): Long {
        return ChronoUnit.YEARS.between(
            Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()),
            Instant.now().atZone(ZoneId.systemDefault())
        )
    }

    private fun getFormattedDateTime(year: Int, month: Int, day: Int): String {
        val timeInMillis = getTimeInMillis(year, month, day)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(Date(timeInMillis))
    }

    private fun closeFragment() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): AddUserFragment {
            return AddUserFragment()
        }
    }
}