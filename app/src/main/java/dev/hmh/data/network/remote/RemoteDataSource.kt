package dev.hmh.ammmvvm.data.network.remote

import dev.hmh.ammmvvm.data.network.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun addUser(userName: String, email: String, password: String) =
        apiService.addUser(username = userName, email = email, password = password)

}