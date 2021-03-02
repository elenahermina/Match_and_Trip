package com.example.matchtrip

import androidx.lifecycle.ViewModel

class TripViewModel: ViewModel() {

    val list = mutableListOf(

        Trip( "European Dream (10 Days)", R.mipmap.europe),

        Trip( "Thai Island Hopper East (9 Days)", R.mipmap.thailanda),

        Trip("Cycle Tour In Albania - UNESCO 10 Day Tour", R.mipmap.albania),

        Trip( "New Zealand: Best of the North Island", R.mipmap.newzeeland))
}