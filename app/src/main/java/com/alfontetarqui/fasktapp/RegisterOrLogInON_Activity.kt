package com.alfontetarqui.fasktapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alfontetarqui.fasktapp.databinding.ActivityRegisterOrLogInOnBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.alfontetarqui.fasktapp.ProviderType // Importa el enum

class RegisterOrLogInON_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterOrLogInOnBinding
    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                showHome(account.email ?: "", ProviderType.GOOGLE)
                            } else {
                                AlertErrorAuthentication()
                            }
                        }
                }
            } catch (e: ApiException) {
                AlertErrorAuthentication()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa View Binding
        binding = ActivityRegisterOrLogInOnBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnregisterON.setOnClickListener { registerONLINE() }
        binding.TxtBtnLogIn.setOnClickListener { loginWithEmail() }
        binding.btnConnectGoogle.setOnClickListener { loginWithGoogle() }
        session()
    }

    override fun onStart() {
        super.onStart()
        binding.mainAuthON.visibility = View.VISIBLE
    }

    private fun session() {
        val prefs = getSharedPreferences(
            getString(R.string.prefs_files),
            Context.MODE_PRIVATE
        )
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            try {
                val providerType = ProviderType.valueOf(provider)
                binding.mainAuthON.visibility = View.INVISIBLE
                showHome(email, providerType)
            } catch (e: IllegalArgumentException) {
                // Maneja el caso donde el valor del provider no es válido
                AlertErrorAuthentication()
            }
        }
    }

    private fun loginWithGoogle() {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut() // Cerrar sesión previa
        googleSignInLauncher.launch(googleClient.signInIntent)
    }

    private fun registerONLINE() {
        val intent = Intent(this@RegisterOrLogInON_Activity, Register_ON_Activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginWithEmail() {
        val intent = Intent(this@RegisterOrLogInON_Activity, LoginON_Activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun AlertErrorAuthentication() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al autenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val intent = Intent(this@RegisterOrLogInON_Activity, WelcomeOFF_Activity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
        finish()
    }
}
