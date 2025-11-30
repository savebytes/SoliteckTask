package com.savebytes.solitecktask.presentation.states

import com.savebytes.solitecktask.data.models.Data

data class HomeState(


    val demo : String = "Demo Value",

    val isNetworkCallSuccessful : Boolean = false,

    val movieHomeData : Data = Data(),

    val isLoading: Boolean = true,

    )