package com.alfontetarqui.fasktapp

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alfontetarqui.fasktapp.databinding.FragmentPMainTimerMenuSessionTimerFormStartBinding

class PMainTimerMenuSessionTimerFormStartFragment : Fragment() {

    private var _binding: FragmentPMainTimerMenuSessionTimerFormStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPMainTimerMenuSessionTimerFormStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnGoPMainTimerMenuTimeControllerStartFragment.setOnClickListener {
            val focusTime = binding.EdtTxtFocusTimePMainTimerMenuTimeControllerStartFragment.text.toString()
            val breakTime = binding.EdtTxtBreakTimePMainTimerMenuTimeControllerStartFragment.text.toString()

            if (validateTimes(focusTime, breakTime)) {
                val focusTimeInMillis = focusTime.toLong() * 60000
                val breakTimeInMillis = breakTime.toLong() * 60000

                (activity as? TimeLine_Main_Activity)?.startTimerFromFragment(focusTimeInMillis, breakTimeInMillis)
            } else {
                Toast.makeText(context, "Focus time must be <= 60 minutes and break time must be <= 15 minutes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateTimes(focusTime: String, breakTime: String): Boolean {
        if (TextUtils.isEmpty(focusTime) || TextUtils.isEmpty(breakTime)) {
            return false
        }

        val focusTimeInt = focusTime.toIntOrNull() ?: return false
        val breakTimeInt = breakTime.toIntOrNull() ?: return false

        return focusTimeInt in 1..60 && breakTimeInt in 1..15
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PMainTimerMenuSessionTimerFormStartFragment().apply {
            }
    }
}
