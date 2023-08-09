package com.example.learnproject_compose.signIn_signUp.data.repository

import com.example.learnproject_compose.model.LoginModele
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.example.learnproject_compose.signIn_signUp.repository.ReloadUserResponses
import com.example.learnproject_compose.signIn_signUp.repository.RevokeAccessResponses
import com.example.learnproject_compose.signIn_signUp.repository.SendEmailVerificationResponses
import com.example.learnproject_compose.signIn_signUp.repository.SendPasswordResetEmailResponses
import com.example.learnproject_compose.signIn_signUp.repository.SignInResponses
import com.example.learnproject_compose.signIn_signUp.repository.SignUpResponses
import com.example.learnproject_compose.signIn_signUp.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,

    ) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    private val usersRef: CollectionReference = Firebase.firestore.collection("users")


    override suspend fun firebaseSignUpWithEmailAndPassword(
        useName: String,
        email: String,
        password: String
    ): SignUpResponses {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
                .user?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(useName).build()
                )?.await()

            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }


    override suspend fun firebaseLoginAddFireStore(
        name: String?,
        email: String?,
        rolle: String?,
        userId: String?,
        level: String?,
        documentId: String,

        ): SignUpResponses {
        val userFireStore = LoginModele(
            userId = userId,
            name = name,
            email = email,
            rolle = rolle,
            level = level,
            documentId = documentId,
        )
        return try {
            usersRef.document(documentId)
                .set(userFireStore)

            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }


    }


    override suspend fun sendEmailVerification(): SendEmailVerificationResponses {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): SignInResponses {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun reloadFirebaseUser(): ReloadUserResponses {
        return try {
            auth.currentUser?.reload()?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponses {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccess(): RevokeAccessResponses {
        return try {
            auth.currentUser?.delete()?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)


}