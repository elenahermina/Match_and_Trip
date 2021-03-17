package com.example.matchtrip

import androidx.fragment.app.Fragment

class FragmentUsers: Fragment()  {

    companion object {
        fun getFragment(): FragmentUsers {
            return FragmentUsers()
        }
    }
}