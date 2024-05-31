package com.alfontetarqui.fasktapp

import android.content.Intent
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
    }

    private fun WelcomeBtnRegister(){
        val intent = Intent(this@Register_OFF_Activity,WelcomeOFF_Activity::class.java)
        startActivity(intent)
        finish()
    }

}