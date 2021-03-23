package com.example.matchtrip.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matchtrip.LoginActivity
import com.example.matchtrip.R
import com.example.matchtrip.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    companion object{
        fun create(context: Context) {
            val intent = Intent(context, MenuActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeActivity(LoginActivity())

        binding.navigationButton.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.createTrip -> {
                    Log.d(MenuActivity::class.java.name, "Botón 1")
                    changeActivity(LoginActivity())
                    Toast.makeText(this, "Menu 1", Toast.LENGTH_LONG).show()
                }
                R.id.logInActivity -> {
                    Log.d(MenuActivity::class.java.name, "Botón 2")
                    changeActivity(LoginActivity())
                    Toast.makeText(this, "Menu 2", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Log.e(MenuActivity::class.java.name, "Unknown item on navigationView")
                    return@setOnNavigationItemSelectedListener false
                }
            }
            true
        }
    }
    private fun changeActivity(activity: Activity) {
    }
}