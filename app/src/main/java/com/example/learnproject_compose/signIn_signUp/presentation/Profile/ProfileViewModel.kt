package com.example.learnproject_compose.signIn_signUp.presentation.Profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.example.learnproject_compose.signIn_signUp.repository.ReloadUserResponses
import com.example.learnproject_compose.signIn_signUp.repository.RevokeAccessResponses
import com.example.learnproject_compose.signIn_signUp.util.Response
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {
    var revokeAccessResponses by mutableStateOf<RevokeAccessResponses>(Response.Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponses>(Response.Success(false))
        private set



//    init {
//        if (isEmailVerified){
//            loginFirebaseTofireStore()
//        }
//    }

    fun loginFirebaseTofireStore() = viewModelScope.launch {
        val userName = repo.currentUser?.displayName
        val userId = repo.currentUser?.uid.orEmpty()
        val email = repo.currentUser?.email
        repo.firebaseLoginAddFireStore(
                name = userName,
                email = email,
                rolle = "Schuller",
                userId = userId,
                level = "A1",
                documentId = userId,
            )

    }

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Response.Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false


    fun signOut() = repo.signOut()


    fun revokeAccess() = viewModelScope.launch {
        reloadUserResponse = Response.Loading
        reloadUserResponse = repo.revokeAccess()
    }

}