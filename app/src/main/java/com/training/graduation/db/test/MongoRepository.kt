//package com.training.graduation.db.test
//
//import com.training.graduation.db.model.Foundation
//import com.training.graduation.db.model.User
//import org.mongodb.kbson.ObjectId
//
//
//interface MongoRepository {
//
//
//    suspend fun addUser(user: User)
//
//    suspend fun editUserProfile(user: User):Boolean?
//
//    suspend fun deleteUser(id: ObjectId):Boolean?
//
//    suspend fun addFoundation(foundation: Foundation)
//
//    suspend fun editFoundationProfile(foundation: Foundation):Boolean?
//
//    suspend fun deleteFoundation(id: ObjectId):Boolean?
//
//    fun getUserData(id: ObjectId): User?
//
//    fun getFoundationData(id: ObjectId): Foundation?
//
//
//}