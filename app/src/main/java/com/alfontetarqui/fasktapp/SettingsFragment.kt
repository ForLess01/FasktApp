package com.alfontetarqui.fasktapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alfontetarqui.fasktapp.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.providerType.observe(viewLifecycleOwner) { providerType ->
            binding.BtnTypeConnectionToApp.text = providerType.name
            saveProviderToPreferences(providerType.name)
        }

        btnLogOutSession()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveProviderToPreferences(provider: String) {
        val prefs = requireContext().getSharedPreferences(
            getString(R.string.prefs_files),
            Context.MODE_PRIVATE
        ).edit()
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun btnLogOutSession() {
        binding.btnLogOutSession.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), LoginON_Activity::class.java)
            startActivity(intent)
        }
    }
}
