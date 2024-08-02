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
import com.alfontetarqui.fasktapp.adapter.GroupNotesAdapter
import com.alfontetarqui.fasktapp.databinding.FragmentPMainNotesInGroupBinding
import com.alfontetarqui.fasktapp.models.GroupNoteModel
import com.alfontetarqui.fasktapp.models.GroupNotesProvider
import com.alfontetarqui.fasktapp.ui.titleGroupNoteFragment
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date
import java.util.Locale


class PMainNotesInGroupFragment : Fragment() {

    private val DB = FirebaseFirestore.getInstance()
    private var _binding: FragmentPMainNotesInGroupBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GroupNotesAdapter
    private var groupNoteMutableList: MutableList<GroupNoteModel> = GroupNotesProvider.groupNotesListModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPMainNotesInGroupBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.VisibleMainTaskView1.setOnClickListener { launchNewFragment() }
        val email = arguments?.getString("email") ?: ""
        val provider = arguments?.getString("provider") ?: ""
        //binding.appCompatButton1.setOnClickListener { launchNewFragment2() }
        binding.BtnAddGroupNote.setOnClickListener{BtnAddGroupNote()}
    }

    private fun BtnAddGroupNote() {
        val addGroupNoteFragment = titleGroupNoteFragment { title ->
            val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
            val newNote = GroupNoteModel(title.toString(), currentDate)
            adapter.addGroupNoteModel(newNote)
        }
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, addGroupNoteFragment)
            addToBackStack(null)
        }
    }

    private fun initRecyclerView() {
        adapter = GroupNotesAdapter(groupNoteMutableList, ::onItemClick, ::onItemLongClick)
        val recyclerView = binding.recyclerViewNotesGroups
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onItemClick(groupNoteModel: GroupNoteModel) {
        val fragment = NotesFragment.newInstance(groupNoteModel.title)
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, fragment)
            addToBackStack(null)
        }
    }
    private fun onItemLongClick(groupNoteModel: GroupNoteModel, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage("Desea eliminar la GroupNote?")
            .setPositiveButton("Si") { _, _ ->
                groupNoteMutableList.removeAt(position)
                binding.recyclerViewNotesGroups.adapter?.notifyItemRemoved(position)
                Toast.makeText(requireContext(), "GroupNote eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

}