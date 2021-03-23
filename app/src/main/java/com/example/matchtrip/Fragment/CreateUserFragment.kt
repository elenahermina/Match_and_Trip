package com.example.matchtrip.Fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.ViewModel.CreateUserFragmentViewModel
import com.example.matchtrip.User
import com.example.matchtrip.activity.MenuActivityInterface
import com.example.matchtrip.databinding.CreateUserLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateUserFragment (var menuActivityInterface: MenuActivityInterface): Fragment() {

    private lateinit var binding: CreateUserLayoutBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var model: CreateUserFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(CreateUserFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateUserLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        binding.signUp.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val userFirstName = binding.EditTextFirstName.text.trim()
        val userLastName = binding.EditTextLastName.text.trim()
        val userEmail = binding.TextEmailAddress.text.trim()
        val userPassword = binding.TextPassword.text.trim()

        if (userFirstName.isEmpty()) {
            binding.EditTextFirstName.error = "First name is required"
            binding.EditTextFirstName.requestFocus()
            return }
        if (userLastName.isEmpty()) {
            binding.EditTextLastName.error = "Last name is required"
            binding.EditTextLastName.requestFocus()
            return }
        if (userEmail.isEmpty()) {
            binding.TextEmailAddress.error = "Email is required"
            binding.TextEmailAddress.requestFocus()
            return }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            binding.TextEmailAddress.error = "Please provide valid email!"
            binding.TextEmailAddress.requestFocus()
            return }
        if (userPassword.isEmpty()) {
            binding.TextPassword.error = "Password is required"
            binding.TextPassword.requestFocus()
            return }
        if (userPassword.length < 8) {
            binding.TextPassword.error = "Password need to be 8 or more letters"
            binding.TextPassword.requestFocus() }

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
                    menuActivityInterface.onFragmentBackPress()

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
                        binding.root.context,
                        " USER ADDED !",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    //check exceptions
                    Toast.makeText(
                        binding.root.context,
                        " Failed to register user !",
                        Toast.LENGTH_SHORT
                    ).show() }
            }
    }
}