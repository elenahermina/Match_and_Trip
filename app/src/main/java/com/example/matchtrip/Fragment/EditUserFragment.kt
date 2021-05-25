package com.example.matchtrip.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.matchtrip.R
import com.example.matchtrip.User
import com.example.matchtrip.activity.MenuActivityInterface
import com.example.matchtrip.adapter.UserAdapter
import com.example.matchtrip.databinding.EditProfilUserBinding
import com.example.matchtrip.viewModel.EditUserFragmentViewModel
import java.io.ByteArrayOutputStream

class EditUserFragment(var menuActivityInterface: MenuActivityInterface) : Fragment() {

    private lateinit var binding: EditProfilUserBinding
    private lateinit var model: EditUserFragmentViewModel
    private var adapter = UserAdapter()
    private var bitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditProfilUserBinding.inflate(layoutInflater)

        model = ViewModelProvider(this).get(EditUserFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfilUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.aboutMe.movementMethod = ScrollingMovementMethod()
        binding.photoUser.setImageResource(R.mipmap.user2)
        binding.profileFirstName.text.trim()
        binding.profileLastName.text.trim()
        binding.logInEmail.text.trim()
        binding.profilePassword.text.trim()
        binding.aboutMe.text.trim()
        binding.profileAge.text.trim()

        binding.photoUser.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }


        binding.guardad.setOnClickListener {
            val user = User(
                binding.logInEmail.text.toString(),
                binding.profilePassword.text.toString(),
                binding.profileFirstName.text.toString(),
                binding.profileLastName.text.toString(),
                binding.aboutMe.text.toString()
            )
            val bitmap = (binding.photoUser.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = stream.toByteArray()
            user.image = image
            model.insertUser(user)
            menuActivityInterface.goUserProfile()
        }

    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let { uri ->
                    requireActivity().contentResolver.let { contentResolver ->
                        bitmap =
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                                val source = ImageDecoder.createSource(contentResolver, uri)
                                ImageDecoder.decodeBitmap(source)
                            } else {
                                MediaStore.Images.Media.getBitmap(
                                    this.requireActivity().contentResolver,
                                    uri
                                )
                            }
                    }
                }
                binding.photoUser.setImageBitmap(bitmap)
            }
        }


}
