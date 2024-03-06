package com.example.amanshopping.viewmodel

import androidx.lifecycle.ViewModel
import com.example.amanshopping.uitl.RegisterFeildState
import com.example.amanshopping.uitl.RegisterValidation
import com.example.amanshopping.uitl.Resource
import com.example.amanshopping.uitl.emailValid
import com.example.amanshopping.uitl.passwordValidate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val mAuth: FirebaseAuth): ViewModel() {

    private var _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.unSpecified())
    val response: Flow<Resource<FirebaseUser>> = _register

    private val _validation = Channel<RegisterFeildState>()
    val validation = _validation.receiveAsFlow()

    fun createUser(username: String, email: String, password: String) {
        if(extracted(email, password)) {
            runBlocking {
                _register.emit(Resource.Loading())
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    it.user?.let {
                        _register.value = Resource.Success(it)
                    }
                }
                .addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())

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