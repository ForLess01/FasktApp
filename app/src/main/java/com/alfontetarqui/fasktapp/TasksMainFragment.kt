package com.alfontetarqui.fasktapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfontetarqui.fasktapp.adapter.TasksMainAdapter
import com.alfontetarqui.fasktapp.databinding.FragmentTasksMainBinding
import com.alfontetarqui.fasktapp.models.TasksMainModel
import com.alfontetarqui.fasktapp.models.TasksMainProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TasksMainFragment : Fragment() {

    private var _binding: FragmentTasksMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TasksMainAdapter
    private var tasksMainMutableList: MutableList<TasksMainModel> = TasksMainProvider.tasksMainListModels
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.BtnAddTaskMain.setOnClickListener { launchNewFragment() }
        initRecyclerView()
        loadTasksFromFirestore()
    }

    private fun loadTasksFromFirestore() {
        val userId = auth.currentUser?.uid ?: return
        db.collection("users").document(userId).collection("tasks")
            .get()
            .addOnSuccessListener { documents ->
                tasksMainMutableList.clear()
                for (document in documents) {
                    val title = document.getString("title") ?: ""
                    val priority = document.getString("priority") ?: ""
                    tasksMainMutableList.add(TasksMainModel(title, priority, "Academic", "03/08/2024", "12:00", "3", "https://www.google.com", "Busqueda de informacion para el informe..."))
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                showToast("Error loading tasks: ${e.message}")
            }
    }

    private fun launchNewFragment() {
        val newFragment = PMainTasksCreateFormFragment.newInstance { title, priority ->
            tasksMainMutableList.add(TasksMainModel(title, priority, "Academic", "03/08/2024", "12:00", "3", "https://www.google.com", "Busqueda de informacion para el informe..."))
            adapter.notifyItemInserted(tasksMainMutableList.size - 1)
            showToast("Tarea agregada: $title")
        }

        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment)
            addToBackStack(null)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        adapter = TasksMainAdapter(tasksMainMutableList, ::onItemClick, ::onItemLongClick)
        val recyclerView = binding.TaskMainRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun onItemClick(tasksMainModel: TasksMainModel) {
        val fragment = PMainTasksCreateFormFragment.newInstance { title, priority ->
            tasksMainModel.title = title
            tasksMainModel.priority = priority
            adapter.notifyItemChanged(tasksMainMutableList.indexOf(tasksMainModel))
        }
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, fragment)
            addToBackStack(null)
        }
    }

    private fun onItemLongClick(tasksMainModel: TasksMainModel, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage("Do you want to delete this task?")
            .setPositiveButton("Yes") { _, _ ->
                deleteTaskFromFirestore(tasksMainModel)
                tasksMainMutableList.removeAt(position)
                binding.TaskMainRecyclerView.adapter?.notifyItemRemoved(position)
                Toast.makeText(requireContext(), "Task deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun deleteTaskFromFirestore(tasksMainModel: TasksMainModel) {
        val userId = auth.currentUser?.uid ?: return
        db.collection("users").document(userId).collection("tasks")
            .whereEqualTo("title", tasksMainModel.title)
            .whereEqualTo("priority", tasksMainModel.priority)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("users").document(userId).collection("tasks").document(document.id).delete()
                }
            }
            .addOnFailureListener { e ->
                showToast("Error deleting task: ${e.message}")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TasksMainFragment().apply {}
    }
}
