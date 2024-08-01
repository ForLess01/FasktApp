package com.alfontetarqui.fasktapp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.alfontetarqui.fasktapp.databinding.ActivityTimeLineMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TimeLine_Main_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeLineMainBinding
    private lateinit var email: String
    private lateinit var provider: String
    private var timer: CountDownTimer? = null
    private var isRunning = false
    private var focusTimeInMillis: Long = 60000 // Default to 1 minute
    private var breakTimeInMillis: Long = 60000 // Default to 1 minute

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line_main)

        // Recupera el email y el provider del Intent
        email = intent.getStringExtra("email") ?: ""
        provider = intent.getStringExtra("provider") ?: ""

        binding = ActivityTimeLineMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.TimerClockForMains.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
                startTimer(focusTimeInMillis)
            }
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment_container,
                TimeLineWeekFragment()
            ).commit()
        }
    }

    fun startTimerFromFragment(focusTimeInMillis: Long, breakTimeInMillis: Long) {
        this.focusTimeInMillis = focusTimeInMillis
        this.breakTimeInMillis = breakTimeInMillis
        startTimer(focusTimeInMillis)
    }

    private fun startTimer(timeInMillis: Long) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                focusTimeInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                isRunning = false
                updateCountDownText()
                // Optionally, start break timer here
                // startTimer(breakTimeInMillis)
            }
        }.start()

        isRunning = true
    }

    private fun pauseTimer() {
        timer?.cancel()
        isRunning = false
    }

    private fun updateCountDownText() {
        val minutes = (focusTimeInMillis / 1000) / 60
        val seconds = (focusTimeInMillis / 1000) % 60

        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.TimerClockForMains.text = timeFormatted
    }

    private fun launchNewFragment3() {
        val newFragment = PMainTimerMenuTimeControllerEndGoodFragment() // Crear una instancia del nuevo fragmento

        // Realizar la transacción del fragmento
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment_container, newFragment) // Asegúrate de que nav_host_fragment_container es el ID correcto del contenedor del fragmento
            addToBackStack(null) // Opcional: agrega esta transacción a la pila hacia atrás para que el usuario pueda volver con el botón de retroceso
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_notes -> selectedFragment = NotesMainFragment()
            R.id.navigation_Tasks -> selectedFragment = TasksMainFragment()
            R.id.navigation_TimeLine -> selectedFragment = TimeLineWeekFragment()
            R.id.navigation_Settings -> {
                selectedFragment = SettingsFragment().apply {
                    arguments = Bundle().apply {
                        putString("email", email)
                        putString("provider", provider)
                    }
                }
            }
            R.id.navigation_timerMenu -> selectedFragment = TimerMenuMainsFragment()
        }

        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment_container,
                it
            ).commit()
        }

        true
    }
}
