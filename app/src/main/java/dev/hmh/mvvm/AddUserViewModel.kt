package dev.hmh.ammmvvm.mvvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hmh.ammmvvm.data.model.LoginResponse
import dev.hmh.ammmvvm.data.repository.AppRepository
import dev.hmh.ammmvvm.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {


    private val _response: MutableLiveData<ApiResponse<LoginResponse>> = MutableLiveData()

    val response: LiveData<ApiResponse<LoginResponse>> = _response
   // var isLoading = mutableStateOf(false)

    fun addUser(userName: String, email: String, password: String) {
        viewModelScope.launch {
            appRepository.addUser(userName, email, password).collect { value ->
                _response.value = value
               /* if (value is ApiResponse.Success) {
                    isLoading.value = true
                    _response.value = value
                }*/

            }
        }
    }

    /*fun addUser2(userName: String, email: String, password: String): ApiResponse<LoginResponse> {
        val result = appRepository.addUser(userName, email, password)
    }*/

}
