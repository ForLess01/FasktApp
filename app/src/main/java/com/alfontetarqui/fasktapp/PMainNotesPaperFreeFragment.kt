package com.alfontetarqui.fasktapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alfontetarqui.fasktapp.databinding.FragmentPMainNotesPaperFreeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PMainNotesPaperFreeFragment : Fragment() {

    private var _binding: FragmentPMainNotesPaperFreeBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private var noteId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            noteId = it.getString("noteId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPMainNotesPaperFreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnSaveNotes.setOnClickListener { saveNoteContent() }

        // Load the note content if it exists
        loadNoteContent()
    }

    private fun loadNoteContent() {
        val userId = auth.currentUser?.uid ?: return
        db.collection("users").document(userId).collection("notes").document(noteId ?: return)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val content = document.getString("content") ?: ""
                    binding.noteEditText.setText(content)
                }
            }
            .addOnFailureListener { e ->
                showToast("Error loading note: ${e.message}")
            }
    }

    private fun saveNoteContent() {
        val userId = auth.currentUser?.uid

        if (userId == null) {
            showToast("Usuario no autenticado")
            return
        }

        val noteContent = binding.noteEditText.text.toString()

        if (noteContent.isEmpty()) {
            showToast("El contenido de la nota no puede estar vacío")
            return
        }

        val noteData = hashMapOf(
            "content" to noteContent
        )

        db.collection("users").document(userId).collection("notes").document(noteId ?: return)
            .set(noteData)
            .addOnSuccessListener {
                showToast("Nota guardada con éxito")
            }
            .addOnFailureListener { e ->
                showToast("Error guardando la nota: ${e.message}")
            }
    }


    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(noteId: String) =
            PMainNotesPaperFreeFragment().apply {
                arguments = Bundle().apply {
                    putString("noteId", noteId)
                }
            }
    }
}
