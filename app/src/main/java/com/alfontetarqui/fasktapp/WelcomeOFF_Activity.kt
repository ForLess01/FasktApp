package com.alfontetarqui.fasktapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alfontetarqui.fasktapp.databinding.ActivityWelcomeOffBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeOFF_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeOffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeOffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verifica la conexión a Internet
        checkInternetConnection()

        // Lanza la siguiente actividad después de 3 segundos
        lifecycleScope.launch {
            delay(3000)
            // Recupera el email y el provider del Intent
            val email = intent.getStringExtra("email") ?: ""
            val provider = intent.getStringExtra("provider") ?: ""

            // Crear el Intent para TimeLine_Main_Activity y pasar los datos
            val intent = Intent(this@WelcomeOFF_Activity, TimeLine_Main_Activity::class.java).apply {
                putExtra("email", email)
                putExtra("provider", provider)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun checkInternetConnection() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        val isConnected = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

        if (isConnected) {
            binding.TextOnlineOFF.text = "ONLINE"
        } else {
            binding.TextOnlineOFF.text = "OFFLINE"
        }
    }
}
