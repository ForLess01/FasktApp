package com.alfontetarqui.fasktapp.ui

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfontetarqui.fasktapp.databinding.FragmentTitleFreeNoteBinding

class titleFreeNoteFragment(private val onNoteAdded: (String) -> Unit) : Fragment() {
    private lateinit var binding: FragmentTitleFreeNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTitleFreeNoteBinding.inflate(inflater, container, false)

        binding.BtnAddFreeNoteCard.setOnClickListener { checkAndBackAddFreeNote() }
        binding.EdtTxtActivityNamePMainTimerMenuTimeControllerStartFragment.text = Editable.Factory.getInstance().newEditable("")

        return binding.root
    }

    private fun checkAndBackAddFreeNote() {
        val titleText = binding.EdtTxtActivityNamePMainTimerMenuTimeControllerStartFragment.text.toString()
        if (titleText.isEmpty()) {
            showToast("Necesita agregar un título a la FreeNote")
        } else {
            onNoteAdded(titleText)
            showToast("FreeNote Agregada")
            parentFragmentManager.popBackStack()
        }
    }

    private fun showToastAndNavigateBack(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        // Retroceder al fragmento anterior
        parentFragmentManager.popBackStack()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}