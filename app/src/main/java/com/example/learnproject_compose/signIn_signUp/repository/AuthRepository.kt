package com.example.learnproject_compose.signIn_signUp.repository

import com.example.learnproject_compose.signIn_signUp.util.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponses = Response<Boolean>
typealias SendEmailVerificationResponses = Response<Boolean>
typealias SignInResponses = Response<Boolean>
typealias ReloadUserResponses = Response<Boolean>
typealias SendPasswordResetEmailResponses = Response<Boolean>
typealias RevokeAccessResponses = Response<Boolean>
typealias AuthStateResponses = StateFlow<Boolean>

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(
        userName: String, email: String, password: String
    ): SignUpResponses


    suspend fun firebaseLoginAddFireStore(
        name: String?,
        email: String?,
        rolle: String?,
        userId: String?,
        level: String?,
        documentId: String,

        ): SignUpResponses

    suspend fun sendEmailVerification(): SendEmailVerificationResponses

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponses

    suspend fun reloadFirebaseUser(): ReloadUserResponses

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponses

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponses

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponses


}