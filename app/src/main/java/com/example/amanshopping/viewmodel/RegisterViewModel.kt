package com.example.amanshopping.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amanshopping.uitl.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val mAuth: FirebaseAuth): ViewModel() {

    private var _register = MutableLiveData<Resource<FirebaseUser>>(Resource.Loading())
    val response: LiveData<Resource<FirebaseUser>> = _register


    fun createUser(username: String, email: String, password: String) {
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

}