package com.example.learnproject_compose.signIn_signUp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {


    init {
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerfied get() = repo.currentUser?.isEmailVerified ?: false





}

