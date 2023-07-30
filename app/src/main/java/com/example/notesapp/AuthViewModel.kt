package com.example.notesapp

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Repository.UserRepository
import com.example.notesapp.model.UserRequest
import com.example.notesapp.model.UserResponse
import com.example.notesapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.Inet4Address
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: UserRepository):ViewModel() {

         val userResponseLiveData:LiveData<NetworkResult<UserResponse>>
         get() = repository.userResponseLiveData

        fun registerUser(userRequest: UserRequest){
            viewModelScope.launch {
                repository.registerUser(userRequest)
            }
        }

        fun loginUSer(userRequest: UserRequest){
            viewModelScope.launch {
                repository.loginUser(userRequest)
            }
        }

        fun validateCredential(username:String , emailAddress: String , password: String, isLogin:Boolean):Pair<Boolean , String> {
            var result = Pair(true , "")

            if((!isLogin && TextUtils.isEmpty(username)) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)){
                result = Pair(false , "Please Provide all credential")
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                result = Pair(false , "Invalid Email-Address{$emailAddress}")
            }
            else if(password.length <= 5) {
                result = Pair(false , "Password length must be grater than 5")
            }
            return  result
        }
}