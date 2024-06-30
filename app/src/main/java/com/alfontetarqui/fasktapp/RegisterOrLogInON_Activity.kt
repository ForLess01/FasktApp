package com.alfontetarqui.fasktapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alfontetarqui.fasktapp.databinding.ActivityRegisterOrLogInOnBinding

class RegisterOrLogInON_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterOrLogInOnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa View Binding
        binding = ActivityRegisterOrLogInOnBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnregisterON.setOnClickListener {registerONLINE()}

        // Aplica los WindowInsets usando View Binding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun registerONLINE(){
        val intent = Intent(this@RegisterOrLogInON_Activity,Register_ON_Activity::class.java)
        startActivity(intent)
        finish()
    }
}
