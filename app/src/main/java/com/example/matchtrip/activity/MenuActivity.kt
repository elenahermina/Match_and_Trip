package com.example.matchtrip.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.matchtrip.R
import com.example.matchtrip.databinding.ActivityMenuBinding
import com.example.matchtrip.fragment.*

interface MenuActivityInterface {
    fun onFragmentBackPress()
    fun goHome()
    fun goUserProfile()
    fun goLogInProfile()
    fun goCreateUser()
    fun goCreateTrip()
    fun goChat()
    fun goEditUser()
}

class MenuActivity : AppCompatActivity(), MenuActivityInterface {
    private lateinit var binding: ActivityMenuBinding

    companion object {
        fun create(context: Context) {
            val intent = Intent(context, MenuActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(MainFragment())

        binding.navigationButton.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Log.d(MenuActivity::class.java.name, "Botón 1")
                    changeFragment(MainFragment())

                }
                R.id.navigation_login_activity -> {
                    Log.d(MenuActivity::class.java.name, "Botón 2")
                    changeFragment(LogInFragment(this))

                }
                R.id.navigation_create_trip -> {
                    Log.d(MenuActivity::class.java.name, "Botón 3")
                    changeFragment(CreateTripFragment(this))

                }
                R.id.navigation_chat -> {
                    Log.d(MenuActivity::class.java.name, "Boton 4")
                    changeFragment(ChatFragment(this))

                }
                else -> {
                    Log.e(MenuActivity::class.java.name, "Unknown item on navigationView")
                    return@setOnNavigationItemSelectedListener false
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayout.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onFragmentBackPress() {
        onBackPressed()
    }

    override fun goHome() {
        changeFragment(MainFragment())
    }

    override fun goUserProfile() {
        changeFragment(UserProfileFragment(this))
    }

    override fun goLogInProfile() {
        changeFragment(LogInFragment(this))
    }

    override fun goCreateUser() {
        changeFragment(CreateUserFragment(this))
    }

    override fun goCreateTrip() {
        changeFragment(CreateUserFragment(this))
    }

    override fun goChat() {
        changeFragment(ChatFragment(this))
    }

    override fun goEditUser() {
        changeFragment(EditUserFragment(this))
    }
}