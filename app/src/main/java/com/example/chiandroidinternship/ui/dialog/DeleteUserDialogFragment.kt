package com.example.chiandroidinternship.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteUserDialogFragment(private val position: Int) : DialogFragment() {
    interface OnDeleteUserListener {
        fun onDeleteUser(position: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("You want to delete this User")
                .setPositiveButton("Yes") { _, _ ->
                    val listener = activity as? OnDeleteUserListener
                    listener?.onDeleteUser(position)
                }
                .setNegativeButton("No") { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}






