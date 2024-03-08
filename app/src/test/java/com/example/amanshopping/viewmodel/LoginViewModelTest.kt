import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.amanshopping.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nhaarman.mockitokotlin2.mock
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testDispatcher: TestCoroutineDispatcher

    private lateinit var viewModel: LoginViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    @Before
    fun setUp() {

        testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)
        firebaseAuth = mock()
        viewModel = LoginViewModel(firebaseAuth)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test login success`() = runBlockingTest {
        // Arrange
        val email = "test@example.com"
        val password = "password123"
        val mockUser = mock<FirebaseUser>()

        `when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(any())

        // Act
      viewModel.login(email,password)
        val result = viewModel.result
        assertEquals(result,mockUser)

    }

//    @Test
//    fun `test login failure`() = runBlockingTest {
//        // Arrange
//        val email = "test@example.com"
//        val password = "invalidpassword"
//        val errorMessage = "Login failed"
//        val authException = FirebaseAuthException(errorMessage, "code")
//
//        `when`(firebaseAuth.signInWithEmailAndPassword(email, password))
//            .thenReturn(any())
//
//        // Act
//        viewModel.login(email, password)
//
//        // Assert
//        val result = viewModel.result.toList()
//        assertEquals(2, result.size) // Loading and Error
//        assertTrue(result[0] is Resource.Loading)
//        assertTrue(result[1] is Resource.Error)
//        assertEquals(errorMessage, (result[1] as Resource.Error).message)
//    }


}
