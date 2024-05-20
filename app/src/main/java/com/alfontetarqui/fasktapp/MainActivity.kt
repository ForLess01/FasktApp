package com.alfontetarqui.fasktapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alfontetarqui.fasktapp.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            // Esperar 4 segundos
            delay(3000)
            // Crear el Intent para la ActivityB
            val intent = Intent(this@MainActivity, Register_OFF_Activity::class.java)
            // Iniciar la ActivityB
            startActivity(intent)
            // Finalizar la ActivityA (opcional)
            finish()
        }
    }
}