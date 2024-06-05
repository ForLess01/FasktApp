package com.alfontetarqui.fasktapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.alfontetarqui.fasktapp.databinding.FragmentTimeLineWeekBinding

class TimeLineWeekFragment : Fragment() {

    private var _binding: FragmentTimeLineWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeLineWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Act1Thursday.setOnClickListener { launchNewFragment() }
    }

    private fun launchNewFragment() {
        val newFragment = PMainTimeLineAcademicFormFragment() // Crear una instancia del nuevo fragmento

        // Realizar la transacción del fragmento
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment) // Asegúrate de que nav_host_fragment_container es el ID correcto del contenedor del fragmento
            addToBackStack(null) // Opcional: agrega esta transacción a la pila hacia atrás para que el usuario pueda volver con el botón de retroceso
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimeLineWeekFragment().apply {
                arguments = Bundle().apply {
                    putString("param1", param1)
                    putString("param2", param2)
                }
            }
    }
}
