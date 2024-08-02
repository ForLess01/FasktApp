package com.alfontetarqui.fasktapp

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfontetarqui.fasktapp.adapter.FreeNotesAdapter
import com.alfontetarqui.fasktapp.databinding.FragmentNotesMainBinding
import com.alfontetarqui.fasktapp.models.FreeNoteModel
import com.alfontetarqui.fasktapp.models.FreeNotesProvider
import com.alfontetarqui.fasktapp.ui.titleFreeNoteFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.Date
import java.util.Locale

class NotesMainFragment : Fragment() {

    private val DB = FirebaseFirestore.getInstance()
    private var _binding: FragmentNotesMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FreeNotesAdapter
    private var freeNoteMutableList: MutableList<FreeNoteModel> = FreeNotesProvider.freeNotesListModels


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
        val email = arguments?.getString("email") ?: ""
        val provider = arguments?.getString("provider") ?: ""
        binding.appCompatButton1.setOnClickListener { launchNewFragment2() }
        binding.BtnAddFreeNote.setOnClickListener{BtnAddFreeNote()}
    }
    //val inputTextBoton = layoutInflater.inflate()
    //DB.collection("freeNotes").document(email).set(
    //hashMapOf("provider" to provider, "titleFreeNote" to FreeNoteTextView.text.toString())
    //)
    private fun BtnAddFreeNote() {
        val addFreeNoteFragment = titleFreeNoteFragment { title ->
            val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(java.util.Date())
            val newNote = FreeNoteModel(title.toString(), currentDate)
            adapter.addFreeNoteModel(newNote)
        }
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, addFreeNoteFragment)
            addToBackStack(null)
        }
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
        adapter = FreeNotesAdapter(freeNoteMutableList)
        val recyclerView = binding.recyclerViewNotes
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}