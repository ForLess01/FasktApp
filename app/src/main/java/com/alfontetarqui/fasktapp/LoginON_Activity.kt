package com.alfontetarqui.fasktapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alfontetarqui.fasktapp.databinding.ActivityLoginOnBinding
import com.google.firebase.auth.FirebaseAuth

class LoginON_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        binding.btnLogInOFFLINE.setOnClickListener {
            if (binding.UserRegisterEmailBoxTxt.text.isNotEmpty() &&
                binding.UserRegisterPasswordBoxTxt.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.UserRegisterEmailBoxTxt.text.toString(),
                    binding.UserRegisterPasswordBoxTxt.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val email = binding.UserRegisterEmailBoxTxt.text.toString()
                        val provider = "Email"  // Cambia esto al provider correspondiente
                        GoLogin(email, provider)
                    } else {
                        AlertErrorAutentication()
                    }
                }
            } else {
                EmptyRegisterCamps()
            }
        }
    }

    private fun AlertErrorAutentication() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al autenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun GoLogin(email: String, provider: String) {
        val intent = Intent(this, WelcomeOFF_Activity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider)
        }
        startActivity(intent)
    }

    private fun EmptyRegisterCamps() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Inicio de Sesión Fallido")
        builder.setMessage("Rellene los campos")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
}
