package com.musabapps.colorfultodos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.todosListFragment
            )
        )
        val controller = findNavController(R.id.nav_host_fragment_container)

        NavigationUI.setupWithNavController(toolbar, controller, appBarConfiguration)


    }
}
