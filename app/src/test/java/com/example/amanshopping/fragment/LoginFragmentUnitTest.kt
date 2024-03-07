package com.example.amanshopping.fragment


import com.example.amanshopping.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class LoginFragmentUnitTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var fragment: LoginFragment
    private lateinit var firebaseAuth: FirebaseAuth

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        firebaseAuth = Mockito.mock(FirebaseAuth::class.java)
        viewModel = LoginViewModel(firebaseAuth)
        fragment = LoginFragment()
        Dispatchers.setMain(testDispatcher)
    }


}
