package com.example.amanshopping.viewmodel

import androidx.lifecycle.ViewModel
import com.example.amanshopping.Constant.USER_COLLECTION
import com.example.amanshopping.model.User
import com.example.amanshopping.uitl.RegisterFeildState
import com.example.amanshopping.uitl.RegisterValidation
import com.example.amanshopping.uitl.Resource
import com.example.amanshopping.uitl.emailValid
import com.example.amanshopping.uitl.passwordValidate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val mAuth: FirebaseAuth,
    val db:FirebaseFirestore
    ): ViewModel() {

    private var _register = MutableStateFlow<Resource<User>>(Resource.unSpecified())
    val response: Flow<Resource<User>> = _register

    private val _validation = Channel<RegisterFeildState>()
    val validation = _validation.receiveAsFlow()

    fun createUser(username: String, email: String, password: String) {
        val user = User(username,email,password)
        if(extracted(email, password)) {
            runBlocking {
                _register.emit(Resource.Loading())
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    it.user?.let {
                        saveUserInfo(it.uid, user )
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

    private fun saveUserInfo(userUid: String,user: User) {
db.collection(USER_COLLECTION)
    .document(userUid)
    .set(user)
    .addOnSuccessListener {
  _register.value = Resource.Success(user)

    }
    .addOnFailureListener {
        _register.value = Resource.Error(it.message.toString())

    }
    }

    private fun extracted(email: String, password: String): Boolean {
        val emailValid = emailValid(email)
        val passwordValid = passwordValidate(password)
        return emailValid is RegisterValidation.Succes && passwordValid is RegisterValidation.Succes
    }

}