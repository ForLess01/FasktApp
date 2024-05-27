package com.alfontetarqui.fasktapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.alfontetarqui.fasktapp.databinding.ActivityTimeLineMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TimeLine_Main_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeLineMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line_main)

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

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_notes -> selectedFragment = NotesMainFragment()
            R.id.navigation_Tasks -> selectedFragment = TasksMainFragment()
            R.id.navigation_TimeLine -> selectedFragment = TimeLineWeekFragment()
            R.id.navigation_Settings -> selectedFragment = SettingsFragment()
            R.id.navigation_timerMenu -> selectedFragment = TimerMenuMainsFragment()
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment_container,
            selectedFragment!!
        ).commit()

        true
    }
}