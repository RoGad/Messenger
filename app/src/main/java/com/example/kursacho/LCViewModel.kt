package com.example.kursacho

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kursacho.data.USER_NODE
import com.example.kursacho.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LCViewModel @Inject constructor(
    val auth: FirebaseAuth,
    var db: FirebaseFirestore

): ViewModel() {
    init {

    }
    var inProcess = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String>?>(null)
    var signIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    fun signUp(name:String, number: String, email: String, password: String){
        inProcess.value = true
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if (it.isSuccessful){
                signIn.value = true
                createOrUpdateProfile(name, number)
                Log.d("TAG", "signUp: User Logger")
            } else {
                handleException(it.exception, customMessage = "SignUp failed")

            }
        }
    }

    fun createOrUpdateProfile(name: String?=null, number: String?=null, imageurl: String?=null){

        var uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name?: userData.value?.name,
            number = number?: userData.value?.number,
            imageUrl = imageurl?:userData.value?.imageUrl
        )

        uid?.let {
            inProcess.value = true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                if(it.exists()){
//                    update user data
                }else{
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProcess.value = false
                    getUserData(uid)
                }

            }
                .addOnFailureListener{
                    handleException(it, "Cannot Retrieve User")
                }
        }

    }

    private fun getUserData(uid: String) {
        inProcess.value = true
        db.collection(USER_NODE).document(uid).addSnapshotListener{
            value, error ->

            if(value != null){
                handleException(error, "Con not retrieve user")
            }

            if(value != null){
                var user = value.toObject<UserData>()
                userData.value = user
                inProcess.value = false
            }
        }
    }

    fun handleException(exception: Exception?=null, customMessage:String=""){
        Log.e("TrackEvents","track events exception:", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage?:""
        val message = if(customMessage.isNullOrEmpty()) errorMsg else customMessage

        eventMutableState.value = Event(message)
        inProcess.value = false


    }


}