package com.example.amanshopping.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amanshopping.uitl.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val _result = MutableSharedFlow<Resource<FirebaseUser>>()
    val result = _result.asSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch{_result.emit(Resource.Loading())}
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    it.user?.let {
                        _result.emit(Resource.Success(it))
                    }

                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                        _result.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}

