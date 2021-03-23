package com.example.matchtrip.Activity

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.ViewModel.RegisterUserActivityViewModel
import com.example.matchtrip.User
import com.example.matchtrip.databinding.CreateUserLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var registerBinding: CreateUserLayoutBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var model: RegisterUserActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = CreateUserLayoutBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        model = ViewModelProvider(this).get(RegisterUserActivityViewModel::class.java)

        mAuth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        registerBinding.signUp.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val userFirstName = registerBinding.EditTextFirstName.text.trim()
        val userLastName = registerBinding.EditTextLastName.text.trim()
        val userEmail = registerBinding.TextEmailAddress.text.trim()
        val userPassword = registerBinding.TextPassword.text.trim()

        if (userFirstName.isEmpty()) {
            registerBinding.EditTextFirstName.error = "First name is required"
            registerBinding.EditTextFirstName.requestFocus()
            return }
        if (userLastName.isEmpty()) {
            registerBinding.EditTextLastName.error = "Last name is required"
            registerBinding.EditTextLastName.requestFocus()
            return }
        if (userEmail.isEmpty()) {
            registerBinding.TextEmailAddress.error = "Email is required"
            registerBinding.TextEmailAddress.requestFocus()
            return }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            registerBinding.TextEmailAddress.error = "Please provide valid email!"
            registerBinding.TextEmailAddress.requestFocus()
            return }
        if (userPassword.isEmpty()) {
            registerBinding.TextPassword.error = "Password is required"
            registerBinding.TextPassword.requestFocus()
            return }
        if (userPassword.length < 8) {
            registerBinding.TextPassword.error = "Password need to be 8 or more letters"
            registerBinding.TextPassword.requestFocus() }

        mAuth.createUserWithEmailAndPassword(userEmail.toString(), userPassword.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //get user ID
                    val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                    // write to database
                    writeNewUser(
                        userId, userFirstName.toString(),
                        userLastName.toString(),
                        userEmail.toString(),
                        userPassword.toString())
                    //redirect to login layout
                    onBackPressed()
                }
            }
    }
    // function for creating new user  and check can we create a new one
    private fun writeNewUser(userId: String, firstName: String, lastName: String, email: String, password: String) {

        val user = User(firstName, lastName, email, password)
        database.child("Users").child(userId).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@RegisterUserActivity,
                        " USER ADDED !",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    //check exceptions
                    Toast.makeText(
                        this@RegisterUserActivity,
                        " Failed to register user !",
                        Toast.LENGTH_SHORT
                    ).show() }
            }
    }
}