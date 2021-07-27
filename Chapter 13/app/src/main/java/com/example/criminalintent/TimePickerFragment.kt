package com.example.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"

class TimePickerFragment : DialogFragment() {
    interface Callbacks {
        fun onTimeSelected(hour: Int, minute: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(hour, minute)
            }
        }

        val time = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = time
        val initialHour = Calendar.HOUR
        val initialMinite = Calendar.MINUTE

        return TimePickerDialog(
            requireContext(), null, initialHour, initialMinite, false)
    }

    companion object {
        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)
            }

            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}