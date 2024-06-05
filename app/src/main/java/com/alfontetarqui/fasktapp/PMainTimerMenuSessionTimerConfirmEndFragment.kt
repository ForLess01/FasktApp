package com.alfontetarqui.fasktapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.alfontetarqui.fasktapp.databinding.FragmentPMainTimerMenuSessionTimerConfirmEndBinding

class PMainTimerMenuSessionTimerConfirmEndFragment : Fragment() {

    private var _binding: FragmentPMainTimerMenuSessionTimerConfirmEndBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPMainTimerMenuSessionTimerConfirmEndBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnResumePMainTimerMenuTimeControllerStartFragment.setOnClickListener { launchNewFragment() }
        binding.BtnEndPMainTimerMenuTimeControllerStartFragment.setOnClickListener { launchNewFragment2() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun launchNewFragment() {
        val newFragment = TasksMainFragment()

        // Realizar la transacción del fragmento
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment)
            addToBackStack(null)
        }
    }
    private fun launchNewFragment2() {
        val newFragment = PMainTimerMenuTimeControllerEndConfirmFragment()

        // Realizar la transacción del fragmento
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment)
            addToBackStack(null)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PMainTimerMenuSessionTimerConfirmEndFragment().apply {
            }
    }
}
