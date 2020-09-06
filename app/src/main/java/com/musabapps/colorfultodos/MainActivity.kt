package com.musabapps.colorfultodos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.musabapps.colorfultodos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.todosListFragment
            )
        )

        val controller = findNavController(R.id.nav_host_fragment_container)
        NavigationUI.setupWithNavController(binding.toolbar, controller, appBarConfiguration)

        

    }


}
