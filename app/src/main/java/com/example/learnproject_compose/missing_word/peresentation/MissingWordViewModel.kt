package com.example.learnproject_compose.missing_word.peresentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnproject_compose.missing_word.data.repository.UsersModel
import com.example.learnproject_compose.missing_word.local.model.MissingWordModel
import com.example.learnproject_compose.missing_word.local.repository.MissingWordRepo
import com.example.learnproject_compose.model.LoginModele
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissingWordViewModel @Inject constructor(private val repo: MissingWordRepo) : ViewModel() {



    var usersUiState by mutableStateOf<UsersModel?>(null)
        private set



//
    init {
        getUsersFromFireStore()
    }

    private fun getUsersFromFireStore() = viewModelScope.launch {
        val usersRef: CollectionReference = Firebase.firestore.collection("users")
        val userId = repo.hasUser()?.uid.orEmpty()


        repo.getUserFromFireStoreSingle(
            userId = userId, usersRef = usersRef,
            onError = {}){
            usersUiState = it
        }


    }

}