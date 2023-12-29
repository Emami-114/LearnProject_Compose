package com.example.learnproject_compose.missing_word.peresentation.addMissWordCat

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.learnproject_compose.missing_word.local.model.MissWordCat
import com.example.learnproject_compose.missing_word.local.repository.MissingWordRepo
import com.example.learnproject_compose.missing_word.peresentation.MissWordCatUiState
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.model.LoginModele
import com.example.learnproject_compose.navigation.DetailsScreen
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissingWordCatViewModel @Inject constructor(private val repo: MissingWordRepo) : ViewModel() {

    private val hasUser = Firebase.auth.currentUser != null

    private val userId = Firebase.auth.currentUser?.uid.orEmpty()
    private val userName = Firebase.auth.currentUser?.displayName.orEmpty()


    var missWordCatUiState = mutableStateOf(MissWordCatUiState())
        private set

    var addMissWordCatUiState by mutableStateOf(AddMissWordUiState())



    fun getUserNivua(isUserAdmin: Boolean, userNiva: String) {
        if (isUserAdmin) {
            getMissWordCatFromFireStore("Alle")
        } else {
            getMissWordCatFromFireStore(userNiva)
        }
    }


    private fun getMissWordCatFromFireStore(level: String?) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("missingWord")

        repo.getMissingWordCatFromFireStore(level, missWordRef).collect {
            missWordCatUiState.value = missWordCatUiState.value.copy(missingWorCatdList = it)

        }

    }


    fun onTitleChange(title: String) {
        addMissWordCatUiState = addMissWordCatUiState.copy(title = title)
    }

    fun onDescription(desc: String) {
        addMissWordCatUiState = addMissWordCatUiState.copy(description = desc)
    }

    fun onLevelChange(level: String) {
        addMissWordCatUiState = addMissWordCatUiState.copy(level = level)
    }

    fun onIsActive(isActive: Boolean) {
        addMissWordCatUiState = addMissWordCatUiState.copy(active = isActive)
    }

    fun onColorChange(color: Int) {
        addMissWordCatUiState = addMissWordCatUiState.copy(color = color)
    }

    fun onNewChange(new: Boolean) {
        addMissWordCatUiState = addMissWordCatUiState.copy(new = new)
    }


    fun addMissingWordToFireStore(navController: NavController) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("missingWord")

        if (hasUser) {
            repo.addMissingWordCatToFireStore(userId = userId,
                title = addMissWordCatUiState.title,
                level = addMissWordCatUiState.level,
                description = addMissWordCatUiState.description,
                isActive = addMissWordCatUiState.active,
                color = addMissWordCatUiState.color,
                timestamp = Timestamp.now(),
                userName = userName,
                missWordRef = missWordRef,
                onNavigate = {
                    navController.navigate(DetailsScreen.AddMissWordPage2.passId(it))
                },
                onComplete = {
                    addMissWordCatUiState = addMissWordCatUiState.copy(addStatus = it)

                })
        }
    }

    fun setEditField(missWordCat: MissWordCat) {
        addMissWordCatUiState =
            addMissWordCatUiState.copy(
                title = missWordCat.title,
                description = missWordCat.description,
                level = missWordCat.level,
                active = missWordCat.active,
                new = missWordCat.new
            )

    }


    fun updateMissingCat(documentId: String, navController: NavController) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("missingWord")

        repo.updateMissWordCategorie(
            title = addMissWordCatUiState.title,
            level = addMissWordCatUiState.level,
            description = addMissWordCatUiState.description,
            color = addMissWordCatUiState.color,
            documentId = documentId,
            isActive = addMissWordCatUiState.active,
            missWordRef = missWordRef,
            new = addMissWordCatUiState.new


            ) {

            addMissWordCatUiState = addMissWordCatUiState.copy(updateMissWordCatStatus = it)

            navController.navigateUp()

        }

    }

    fun updateMissingCatNew(documentId: String) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("missingWord")

        repo.updateMissWordCategorieNew(
            documentId = documentId,
            new = false,
            missWordRef = missWordRef,

            ) {

            addMissWordCatUiState = addMissWordCatUiState.copy(updateMissWordCatStatusNew = it)
        }
    }

    fun updateMissingCatItemCount(documentId: String, itemCount: Int) = viewModelScope.launch {
        val missWordRef = Firebase.firestore.collection("missingWord")

        repo.updateMissWordCategorieItemCount(
            documentId = documentId,
            itemCount = itemCount,
            missWordRef = missWordRef,
            ) {
            addMissWordCatUiState = addMissWordCatUiState.copy(updateMissWordCatStatusNew = it)
        }
    }


    fun getMissWordCatSingle(documentId: String) {
        val missWordRef = Firebase.firestore.collection("missingWord")

        repo.getMissingWordCatSingle(documentId = documentId,
            missWordRef = missWordRef,
            onError = {}) {
            addMissWordCatUiState = addMissWordCatUiState.copy(selectedMissWordCat = it)
            addMissWordCatUiState.selectedMissWordCat?.let { it -> setEditField(it) }

        }
    }

    fun deleteMissWordCat(documentId: String) {
        val missWordRef = Firebase.firestore.collection("missingWord")
        repo.deleteMissWordCat(documentId, missWordRef) {
            addMissWordCatUiState = addMissWordCatUiState.copy(deleteStatus = it)
        }
    }


    fun resetMissWordState() {
        addMissWordCatUiState = AddMissWordUiState()
    }


}

