package com.alfontetarqui.fasktapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.adapter.FreeNotesAdapter
import com.alfontetarqui.fasktapp.databinding.FragmentNotesMainBinding
import com.alfontetarqui.fasktapp.models.FreeNote
import com.alfontetarqui.fasktapp.models.FreeNotesProvider

class NotesMainFragment : Fragment() {

    private var _binding: FragmentNotesMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotesMainBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.VisibleMainTaskView1.setOnClickListener { launchNewFragment() }
        binding.appCompatButton1.setOnClickListener { launchNewFragment2() }
    }

    private fun launchNewFragment() {
        val newFragment = NotesFragment() // Crear una instancia del nuevo fragmento

        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment) // Asegúrate de que fragment_container_view es el ID correcto del contenedor del fragmento
            addToBackStack(null) // Opcional: agrega esta transacción a la pila hacia atrás para que el usuario pueda volver con el botón de retroceso
        }
    }
    private fun launchNewFragment2() {
        val newFragment = PMainNotesInGroupFragment() // Crear una instancia del nuevo fragmento

        // Realizar la transacción del fragmento
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment) // Asegúrate de que fragment_container_view es el ID correcto del contenedor del fragmento
            addToBackStack(null) // Opcional: agrega esta transacción a la pila hacia atrás para que el usuario pueda volver con el botón de retroceso
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerViewNotes
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = FreeNotesAdapter(FreeNotesProvider.FreeNotesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}