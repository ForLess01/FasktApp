package com.alfontetarqui.fasktapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.alfontetarqui.fasktapp.databinding.ActivityTimeLineMainBinding

class TimeLine_Main_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeLineMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTimeLineMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar escuchador de eventos para la barra de navegación
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_calendar -> {
                    replaceFragment(())
                    true
                }

                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.TimeLine_Fragment_Container, fragment)
            .commit()
    }
}