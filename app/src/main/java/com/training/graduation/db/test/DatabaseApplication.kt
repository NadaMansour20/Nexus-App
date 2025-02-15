//package com.training.graduation.db.test
//
//import android.util.Log
//import at.favre.lib.crypto.bcrypt.BCrypt
//import com.mongodb.client.MongoClients
//import com.mongodb.client.MongoCollection
//import com.mongodb.client.MongoDatabase
//import com.training.graduation.db.model.User
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import org.bson.Document
//import org.bson.types.ObjectId
//
//object DatabaseModule {
//
//    private const val DATABASE_NAME = "NexusDatabase"
//    private const val COLLECTION_NAME = "users"
//    private const val MONGO_URI = "mongodb://nexus:nexusMongoDb@cluster0.nc4w0.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
//
//// connect with database
//fun provideMongoDatabase(): MongoDatabase {
//        val client = MongoClients.create(MONGO_URI)
//
//    return client.getDatabase(DATABASE_NAME)
//    }
//
//    suspend fun registerUser(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
//        if (user.email.isNullOrEmpty() || user.password.isNullOrEmpty()) {
//            onError("Email and password cannot be empty.")
//            return
//        }
//
//        withContext(Dispatchers.IO) {
//            try {
//                val database = provideMongoDatabase()
//                val collection: MongoCollection<Document> = database.getCollection(COLLECTION_NAME)
//
//                val existingUser = collection.find(Document("email", user.email)).first()
//                if (existingUser != null) {
//                    withContext(Dispatchers.Main) { onError("User already exists.") }
//                    return@withContext
//                }
//
//
//                //encoded password
//                val hashedPassword = BCrypt.withDefaults().hashToString(12, user.password!!.toCharArray())
//
//                val userDoc = Document()
//                    .append("_id", ObjectId())
//                    .append("name", user.name)
//                    .append("email", user.email)
//                    .append("password", hashedPassword)
//                    .append("host", user.host)
//                    .append("imageFileId", user.imageFileId?.toHexString())
//
//                collection.insertOne(userDoc)
//
//                Log.e("Register", "User added successfully")
//                withContext(Dispatchers.Main) { onSuccess() }
//
//            } catch (e: Exception) {
//                Log.e("Register Failed", "Error while adding user: ${e.message}")
//                withContext(Dispatchers.Main) { onError("Registration failed. Please try again.") }
//            }
//        }
//    }
//
//    suspend fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
//        if (email.isEmpty() || password.isEmpty()) {
//            onError("Email and password cannot be empty.")
//            return
//        }
//
//        withContext(Dispatchers.IO) {
//            try {
//                val database = provideMongoDatabase()
//                val collection: MongoCollection<Document> = database.getCollection(COLLECTION_NAME)
//
//                val userDoc = collection.find(Document("email", email)).first()
//
//                if (userDoc == null) {
//                    withContext(Dispatchers.Main) { onError("User not found.") }
//                    return@withContext
//                }
//
//                // encoded password
//                val storedHashedPassword = userDoc.getString("password")
//                val result = BCrypt.verifyer().verify(password.toCharArray(), storedHashedPassword)
//
//                if (result.verified) {
//                    Log.e("Login", "Login successful")
//                    withContext(Dispatchers.Main) { onSuccess() }
//                } else {
//                    withContext(Dispatchers.Main) { onError("Invalid email or password.") }
//                }
//
//            } catch (e: Exception) {
//                Log.e("Login Failed", "Error while logging in: ${e.message}")
//                withContext(Dispatchers.Main) { onError("Login failed. Please try again.") }
//            }
//        }
//    }
//}
