package com.alfontetarqui.fasktapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.alfontetarqui.fasktapp.databinding.FragmentPMainTimerMenuTimeControllerEndConfirmBinding

class PMainTimerMenuTimeControllerEndConfirmFragment : Fragment() {


    private var _binding: FragmentPMainTimerMenuTimeControllerEndConfirmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPMainTimerMenuTimeControllerEndConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnConfirmPMainTimerMenuTimeControllerStartFragment.setOnClickListener { launchNewFragment() }
    }

    private fun launchNewFragment() {
        val newFragment = PMainTimerMenuTimeControllerEndBadFragment() // Reemplaza AnotherFragment con el fragmento que desees lanzar

        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PMainTimerMenuTimeControllerEndConfirmFragment().apply {

            }
    }
}
