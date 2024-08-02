package com.alfontetarqui.fasktapp.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alfontetarqui.fasktapp.databinding.FragmentTitleGroupNoteBinding

class titleGroupNoteFragment(private val onNoteAdded: (String) -> Unit) : Fragment() {
    private lateinit var binding: FragmentTitleGroupNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentTitleGroupNoteBinding.inflate(inflater, container, false)
        binding = FragmentTitleGroupNoteBinding.inflate(inflater,container,false)
        binding.BtnAddGroupNoteCard.setOnClickListener { checkAndAddGroupNote() }

        binding.EdtTxtActivityNamePMainTimerMenuTimeControllerStartFragment.text = Editable.Factory.getInstance().newEditable("")

        return binding.root
    }

    private fun checkAndAddGroupNote() {
        val titleText = binding.EdtTxtActivityNamePMainTimerMenuTimeControllerStartFragment.text.toString()
        if (titleText.isEmpty()) {
            showToast("Necesita agregar un título a la GroupNote")
        } else {
            onNoteAdded(titleText)
            showToast("GroupNote Agregada")
            parentFragmentManager.popBackStack()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}
