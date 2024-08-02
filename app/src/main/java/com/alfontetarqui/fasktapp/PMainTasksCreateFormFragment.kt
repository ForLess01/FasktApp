package com.alfontetarqui.fasktapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alfontetarqui.fasktapp.databinding.FragmentPMainTasksCreateFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PMainTasksCreateFormFragment : Fragment() {

    private var onTaskAdded: ((String, String) -> Unit)? = null
    private var _binding: FragmentPMainTasksCreateFormBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPMainTasksCreateFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.SaveBtnCreateFormTask.setOnClickListener { checkAndAddTasksMain() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkAndAddTasksMain() {
        val titleText = binding.EdtTxtTaskNameCreateForm.text.toString()
        val priorityText = binding.EdtTxTPriorityCreateFormTask.text.toString()

        if (titleText.isEmpty()) {
            showToast("Necesita agregar un título a la GroupNote")
        } else if (priorityText.isEmpty()) {
            showToast("Necesita agregar una prioridad a la GroupNote")
        } else {
            val priority = when (priorityText.toIntOrNull()) {
                1 -> "P1"
                2 -> "P2"
                3 -> "P3"
                else -> "P?"
            }
            saveTaskToFirestore(titleText, priority)
            onTaskAdded?.invoke(titleText, priority)
            onTaskAdded?.invoke(titleText, priority)
            showToast("Task Added")
            parentFragmentManager.popBackStack()
        }
    }

    private fun saveTaskToFirestore(title: String, priority: String) {
        val userId = auth.currentUser?.uid ?: return
        val task = hashMapOf(
            "title" to title,
            "priority" to priority
        )
        db.collection("users").document(userId).collection("tasks")
            .add(task)
            .addOnSuccessListener {
                showToast("Task saved to Firestore")
            }
            .addOnFailureListener { e ->
                showToast("Error saving task: ${e.message}")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(onTaskAdded: (String, String) -> Unit): PMainTasksCreateFormFragment {
            val fragment = PMainTasksCreateFormFragment()
            fragment.onTaskAdded = onTaskAdded
            return fragment
        }
    }
}
