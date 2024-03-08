package com.example.amanshopping.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amanshopping.data.SharedPreferencesHelper
import com.example.amanshopping.uitl.RegisterFeildState
import com.example.amanshopping.uitl.RegisterValidation
import com.example.amanshopping.uitl.Resource
import com.example.amanshopping.uitl.emailValid
import com.example.amanshopping.uitl.passwordValidate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _result = MutableSharedFlow<Resource<FirebaseUser>>()
    val result = _result.asSharedFlow()

    private val _validation = Channel<RegisterFeildState>()
    val validation = _validation.receiveAsFlow()

    fun login(email: String, password: String) {

        if (extracted(email, password)) {
            viewModelScope.launch { _result.emit(Resource.Loading()) }
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    viewModelScope.launch {
                        it.user?.let {
                            _result.emit(Resource.Success(it))
                            sharedPreferencesHelper.saveLoginStatus(true)
                        }

                    }
                }
                .addOnFailureListener {
                    viewModelScope.launch {
                        _result.emit(Resource.Error(it.message.toString()))
                    }
                }
        }
        else{
            val registerField = RegisterFeildState(
                emailValid(email),passwordValidate(password)
            )
            runBlocking {
                _validation.send(registerField)
            }
        }


    }
    private fun extracted(email: String, password: String): Boolean {
        val emailValid = emailValid(email)
        val passwordValid = passwordValidate(password)
        return emailValid is RegisterValidation.Succes && passwordValid is RegisterValidation.Succes
    }
}

