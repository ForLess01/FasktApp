package com.alfontetarqui.fasktapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alfontetarqui.fasktapp.databinding.ActivityRegisterOnBinding
import com.google.firebase.auth.FirebaseAuth

class Register_ON_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterOnBinding
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa View Binding
        binding = ActivityRegisterOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        binding.btnregisterOFFLINE.setOnClickListener {
            if (binding.UserRegisterNameBoxTxt.text.isNotEmpty() &&
                binding.UserRegisterAgeBoxTxt.text.isNotEmpty() &&
                binding.UserRegisterEmailBoxTxt.text.isNotEmpty() &&
                binding.UserRegisterPasswordBoxTxt.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.UserRegisterEmailBoxTxt.text.toString(),
                    binding.UserRegisterPasswordBoxTxt.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){
                            val userName = binding.UserRegisterNameBoxTxt.text.toString()
                            val providerType = ProviderType.BASIC
                            // Almacenar nombre y proveedor en el ViewModel
                            sharedViewModel.setUserInfo(userName, providerType)
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
        val intent = Intent(this@Register_ON_Activity,LoginON_Activity::class.java).apply {}
        startActivity(intent)
    }
    private fun EmptyRegisterCamps(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro Fallido")
        builder.setMessage("Rellene los campos")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

}
