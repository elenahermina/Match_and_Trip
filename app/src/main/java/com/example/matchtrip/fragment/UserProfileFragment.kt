package com.example.matchtrip.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.R
import com.example.matchtrip.activity.MenuActivityInterface
import com.example.matchtrip.adapter.UserAdapter
import com.example.matchtrip.databinding.ProfileUserBinding
import com.example.matchtrip.viewModel.UserProfileFragmentViewModel

class UserProfileFragment(var menuActivityInterface: MenuActivityInterface) : Fragment() {

    private lateinit var binding: ProfileUserBinding
    private lateinit var model: UserProfileFragmentViewModel
    private var adapter = UserAdapter()

    companion object {
        const val ID_User1 = "VALOR_1"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileUserBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get(UserProfileFragmentViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photoUser.setImageResource(R.mipmap.user2)
        binding.profileFirstName.text.toString()
        binding.profileLastName.text.toString()
        binding.logInEmail.text.toString()
        binding.profilePassword.text.toString()
        binding.aboutMe.text.toString()
        binding.profileAge.text.toString()


        binding.editProfile.setOnClickListener {
            menuActivityInterface.goEditUser()
        }


    }

}