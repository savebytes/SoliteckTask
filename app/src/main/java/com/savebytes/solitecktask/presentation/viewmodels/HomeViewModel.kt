package com.savebytes.solitecktask.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savebytes.solitecktask.data.models.Data
import com.savebytes.solitecktask.domain.repo.HomeRepo
import com.savebytes.solitecktask.presentation.states.HomeState
import com.savebytes.solitecktask.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    companion object {
        const val TAG = "HomeViewModel"
    }

    init {

        viewModelScope.launch {
            val response = homeRepo.getMovies()
            if (response != null) {
                when (response.status) {
                    Status.SUCCESS -> {
                        _homeState.update { it ->
                            it.copy(
                                isNetworkCallSuccessful = true,
                                movieHomeData = response.data?.data ?: Data(),
                                isLoading = false
                            )

                        }
                        Log.d(TAG, "Response: ${response.data}")
                    }

                    Status.ERROR -> {
                        _homeState.update { it ->
                            it.copy(isNetworkCallSuccessful = true, isLoading = false)
                        }
                        Log.d(TAG, "Response: ${response.data}")

                    }

                    Status.LOADING -> {
                        _homeState.update { it ->
                            it.copy(isNetworkCallSuccessful = true, isLoading = true)
                        }
                        Log.d(TAG, "Response: ${response.data}")

                    }
                }
            }
        }

    }

}