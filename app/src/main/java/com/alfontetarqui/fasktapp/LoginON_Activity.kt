package com.alfontetarqui.fasktapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alfontetarqui.fasktapp.databinding.ActivityLoginOnBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}
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
                binding.UserRegisterPasswordBoxTxt.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.UserRegisterEmailBoxTxt.text.toString(),
                    binding.UserRegisterPasswordBoxTxt.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        GoLogin()
                    }else{
                        AlertErrorAutentication()
                    }
                }
            }
            else{
                EmptyRegisterCamps()
            }
        }

    }
    private fun AlertErrorAutentication(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al autenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
    //email: String, providerType: ProviderType
    private fun GoLogin(){
        val intent = Intent(this,WelcomeOFF_Activity::class.java).apply {}
        startActivity(intent)
    }
    private fun EmptyRegisterCamps(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Inicio de Sesión Fallido")
        builder.setMessage("Rellene los campos")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
}