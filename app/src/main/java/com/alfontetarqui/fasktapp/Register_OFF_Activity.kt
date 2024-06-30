package com.alfontetarqui.fasktapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfontetarqui.fasktapp.databinding.ActivityRegisterOffBinding

class Register_OFF_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterOffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterOffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnregisterOFFLINE.setOnClickListener { WelcomeBtnRegister() }

        checkInternetConnection()
    }

    private fun WelcomeBtnRegister(){
        val intent = Intent(this@Register_OFF_Activity,WelcomeOFF_Activity::class.java)
        startActivity(intent)
        finish()
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