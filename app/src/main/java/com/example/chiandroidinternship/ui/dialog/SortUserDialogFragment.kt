package com.example.chiandroidinternship.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.chiandroidinternship.R

class SortUserDialogFragment : DialogFragment() {

    interface SortingDialogListener {
        fun onSortByName()
        fun onSortByAge()
        fun onSortByStudentStatus()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = layoutInflater.inflate(R.layout.fragment_sort_user, null)

        val sortByName = dialogView.findViewById<TextView>(R.id.sortByName)
        val sortByAge = dialogView.findViewById<TextView>(R.id.sortByAge)
        val sortByStudentStatus = dialogView.findViewById<TextView>(R.id.sortByStudentStatus)

        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setView(dialogView)
        dialogBuilder.setTitle("Sorting Options")

        dialogBuilder.setPositiveButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        sortByName.setOnClickListener {
            val listener = activity as? SortingDialogListener
            listener?.onSortByName()
            dismiss()
        }

        sortByAge.setOnClickListener {
            val listener = activity as? SortingDialogListener
            listener?.onSortByAge()
            dismiss()
        }

        sortByStudentStatus.setOnClickListener {
            val listener = activity as? SortingDialogListener
            listener?.onSortByStudentStatus()
            dismiss()
        }

        return dialogBuilder.create()
    }
}