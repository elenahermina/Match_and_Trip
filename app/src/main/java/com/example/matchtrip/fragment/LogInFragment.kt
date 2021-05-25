package com.example.matchtrip.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.matchtrip.activity.MenuActivityInterface
import com.example.matchtrip.databinding.LoginLayoutBinding
import com.example.matchtrip.db.Db
import com.example.matchtrip.viewModel.LogInFragmentViewModel
import kotlinx.coroutines.launch


class LogInFragment(var menuActivityInterface: MenuActivityInterface) : Fragment() {

    private lateinit var mainBinding: LoginLayoutBinding
    private lateinit var model: LogInFragmentViewModel
    private val db = Db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(LogInFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = LoginLayoutBinding.inflate(inflater, container, false)

        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get instance of authentication

        // redirect to registration
        mainBinding.create.setOnClickListener {
            menuActivityInterface.goCreateUser()
        }
        // make login in
        mainBinding.button.setOnClickListener {
            mainBinding.textView3.isEnabled && mainBinding.textView3.isChecked

            lifecycleScope.launch {
                model.verifyUser(mainBinding.editTextTextEmailAddress.text.toString())
            }
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
        menuActivityInterface.goUserProfile()

    }

}
