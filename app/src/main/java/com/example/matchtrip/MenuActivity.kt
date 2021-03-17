package com.example.matchtrip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.matchtrip.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {
   lateinit var binding: ActivityMenuBinding

    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context, MenuActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(FragmentTripsList())
        binding.navigationButton.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.navigation_trips -> {
                    showFragment(FragmentTripsList.getFragment())
                    true
                }
                R.id.navigation_users -> {
                    showFragment(FragmentUsers.getFragment())
                    true
                }
                R.id.navigation_chat-> {
                    showFragment(FragmentChat.getFragment())
                    true
                }
                R.id.navigation_search -> {
                    showFragment(FragmentSearch.getFragment())
                    true
                }
                else -> false
            }
        }
    }


    fun showFragment(fragment : Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}
