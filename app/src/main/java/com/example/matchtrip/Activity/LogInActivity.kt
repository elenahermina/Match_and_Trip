package com.example.matchtrip

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.Activity.RegisterUserActivity
import com.example.matchtrip.Activity.UserProfileActivity
import com.example.matchtrip.ViewModel.LogInActivityViewModel
import com.example.matchtrip.databinding.LoginLayoutBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var mainBinding: LoginLayoutBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var model: LogInActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        model = ViewModelProvider(this).get(LogInActivityViewModel::class.java)

        // get instance of authentication
        mAuth = FirebaseAuth.getInstance()

        //redirect registered and LOGGED user in profile
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }

        // redirect to registration
        mainBinding.create.setOnClickListener {
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }
        // make login in
        mainBinding.button.setOnClickListener {
            userLogin()
        }
    }

    private fun userLogin() {
        val userEmail = mainBinding.editTextTextEmailAddress.text.trim()
        val userPassword = mainBinding.editTextTextPassword.text.trim()

        if (userEmail.isEmpty()) {
            mainBinding.editTextTextEmailAddress.error = "Please enter email!"
            mainBinding.editTextTextEmailAddress.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            mainBinding.editTextTextEmailAddress.error = "Please enter valid email!"
            mainBinding.editTextTextEmailAddress.requestFocus()
            return
        }

        if (userPassword.isEmpty()) {
            mainBinding.editTextTextPassword.error = "Please enter password!"
            mainBinding.editTextTextPassword.requestFocus()
            return
        }

        if (userPassword.length < 8) {
            mainBinding.editTextTextPassword.error = "Password length is not enough!"
            mainBinding.editTextTextPassword.requestFocus()
            return
        }

        // user auth with email and password can be changed on any other backend query/call
        mAuth.signInWithEmailAndPassword(userEmail.toString(), userPassword.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //redirect to user profile
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user?.isEmailVerified == true) {
                        startActivity(Intent(this, UserProfileActivity::class.java))
                    } else {
                        user?.sendEmailVerification()
                        Toast.makeText(this, "Email verification sent !", Toast.LENGTH_SHORT).show()
                    }

                }
            }
    }
}