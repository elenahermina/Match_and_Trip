package com.example.matchtrip.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.ViewModel.LogInFragmentViewModel
import com.example.matchtrip.databinding.LoginLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment: Fragment() {
    private lateinit var mainBinding: LoginLayoutBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var model: LogInFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(LogInFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainBinding = LoginLayoutBinding.inflate(inflater, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get instance of authentication
        mAuth = FirebaseAuth.getInstance()
        // redirect to registration
        mainBinding.create.setOnClickListener {
            startActivity(Intent(mainBinding.root.context, CreateUserFragment::class.java))
        }
        // make login in
        mainBinding.button.setOnClickListener {
            userLogin()
        }
    }
    override fun onResume() {
        super.onResume()
        //redirect registered and LOGGED user in profile
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(mainBinding.root.context, UserProfileFragment::class.java))
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
                        startActivity(Intent(mainBinding.root.context, UserProfileFragment::class.java))
                      //  finish()
                    } else {
                        user?.sendEmailVerification()
                        Toast.makeText(mainBinding.root.context, "Email verification sent !", Toast.LENGTH_SHORT).show()
                    }

                }
            }
    }
}
