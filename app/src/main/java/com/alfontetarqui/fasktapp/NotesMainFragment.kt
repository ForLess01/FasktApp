package com.alfontetarqui.fasktapp

import android.app.AlertDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfontetarqui.fasktapp.adapter.FreeNotesAdapter
import com.alfontetarqui.fasktapp.databinding.FragmentNotesMainBinding
import com.alfontetarqui.fasktapp.models.FreeNoteModel
import com.alfontetarqui.fasktapp.models.FreeNotesProvider
import com.alfontetarqui.fasktapp.ui.titleFreeNoteFragment
import com.google.firebase.firestore.FirebaseFirestore
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

        val email = arguments?.getString("email") ?: ""
        val provider = arguments?.getString("provider") ?: ""
        binding.appCompatButton1.setOnClickListener { launchNewFragment2() }
        binding.BtnAddFreeNote.setOnClickListener { BtnAddFreeNote() }
    }

    private fun BtnAddFreeNote() {
        val addFreeNoteFragment = titleFreeNoteFragment { title ->
            val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(java.util.Date())
            val newNote = FreeNoteModel(title, currentDate)
            saveNoteToFirestore(newNote)
            adapter.addFreeNoteModel(newNote)
        }
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, addFreeNoteFragment)
            addToBackStack(null)
        }
    }

    private fun saveNoteToFirestore(note: FreeNoteModel) {
        val email = arguments?.getString("email") ?: return
        val noteData = hashMapOf(
            "title" to note.title,
            "date" to note.date
        )
        DB.collection("freeNotes").document(email).collection("notes")
            .add(noteData)
            .addOnSuccessListener {
                showToast("Nota guardada con éxito")
            }
            .addOnFailureListener { e ->
                showToast("Error guardando la nota: ${e.message}")
            }
    }

    private fun launchNewFragment2() {
        val newFragment = PMainNotesInGroupFragment()
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment)
            addToBackStack(null)
        }
    }

    private fun initRecyclerView() {
        adapter = FreeNotesAdapter(freeNoteMutableList, ::onItemClick, ::onItemLongClick)
        val recyclerView = binding.recyclerViewNotes
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(freeNoteModel: FreeNoteModel) {
        val fragment = PMainNotesPaperFreeFragment.newInstance(freeNoteModel.title)
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, fragment)
            addToBackStack(null)
        }
    }

    private fun onItemLongClick(freeNoteModel: FreeNoteModel, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage("Desea eliminar la FreeNote?")
            .setPositiveButton("Si") { _, _ ->
                deleteNoteFromFirestore(freeNoteModel)
                freeNoteMutableList.removeAt(position)
                binding.recyclerViewNotes.adapter?.notifyItemRemoved(position)
                Toast.makeText(requireContext(), "FreeNote eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun deleteNoteFromFirestore(note: FreeNoteModel) {
        val email = arguments?.getString("email") ?: return
        DB.collection("freeNotes").document(email).collection("notes")
            .whereEqualTo("title", note.title)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    DB.collection("freeNotes").document(email).collection("notes").document(document.id).delete()
                }
            }
            .addOnFailureListener { e ->
                showToast("Error deleting note: ${e.message}")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
