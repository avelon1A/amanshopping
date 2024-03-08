package com.example.amanshopping.di
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.amanshopping.data.SharedPreferencesHelper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore


    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(@ApplicationContext context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }

}


