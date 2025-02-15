//package com.training.graduation.db.test
//
//import com.training.graduation.db.model.Foundation
//import com.training.graduation.db.model.User
//import io.realm.kotlin.Realm
//import org.mongodb.kbson.ObjectId
//
//
//class MongoRepositoryImpl(val realm: Realm) : MongoRepository {
//
//    override suspend fun addUser(user: User) {
//        realm.write {
//            copyToRealm(user)
//        }
//    }
//
//
//    override suspend fun editUserProfile(user: User): Boolean? {
//        return realm.write {
//            val existingUser = query<User>("id == $0", user.id).first().find()
//            existingUser?.apply {
//                name = user.name
//                email = user.email
//                password= user.password
//                host = user.host
//            }
//            existingUser != null
//        }    }
//
//    override suspend fun deleteUser(id: ObjectId): Boolean? {
//        return realm.write {
//            val userToDelete = query<User>("id == $0", id).first().find()
//            if (userToDelete != null) {
//                delete(userToDelete)
//                return@write true
//            }
//            return@write false
//        }    }
//
//    override suspend fun addFoundation(foundation: Foundation) {
//        realm.write {
//            copyToRealm(foundation)
//        }    }
//
//    override suspend fun editFoundationProfile(foundation: Foundation): Boolean? {
//        return realm.write {
//            val existingFoundation = query<Foundation>("id == $0", foundation.id).first().find()
//            existingFoundation?.apply {
//                name = foundation.name
//                email = foundation.email
//                password= foundation.password
//            }
//            existingFoundation != null
//        }    }
//
//    override suspend fun deleteFoundation(id: ObjectId): Boolean? {
//        return realm.write {
//            val foundationToDelete = query<Foundation>("id == $0", id).first().find()
//            if (foundationToDelete != null) {
//                delete(foundationToDelete)
//                return@write true
//            }
//            return@write false
//        }    }
//
//    override fun getUserData(id: ObjectId): User? {
//        return realm.query<User>("id == $0", id).first().find()
//    }
//
//    override fun getFoundationData(id: ObjectId): Foundation? {
//        return realm.query<Foundation>("id== $0", id).first().find()
//    }
//}
