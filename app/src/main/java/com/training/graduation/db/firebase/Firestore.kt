package com.training.graduation.db.firebase

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.training.graduation.db.model.User


fun getCollection(CollectionName: String): CollectionReference {
    val firestore = Firebase.firestore

    //create collection
    return firestore.collection(CollectionName)

}


//register
fun addUserToFirestore(
    user: User,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {

    val collectionUser = getCollection(User.CollectionNameUser)



    //just document ,firebase that is enter id
    //document() , I am who entered the id
    val documentUser = collectionUser.document(user.id!!)


        //new document --> new object
        documentUser.set(user).addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)


}





