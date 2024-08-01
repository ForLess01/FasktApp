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
        binding.TxtBtnLogIn.setOnClickListener {LoginWithEmail()}
        binding.btnConnectGoogle.setOnClickListener {LoginWithGoogle()}
    }

    private fun LoginWithGoogle(){
        val intent = Intent(this@RegisterOrLogInON_Activity,WelcomeOFF_Activity::class.java)
        startActivity(intent)
        finish()
    }
    private fun registerONLINE(){
        val intent = Intent(this@RegisterOrLogInON_Activity,Register_ON_Activity::class.java)
        startActivity(intent)
        finish()
    }
    private fun LoginWithEmail(){
        val intent = Intent(this@RegisterOrLogInON_Activity,LoginON_Activity::class.java)
        startActivity(intent)
        finish()
    }
}
