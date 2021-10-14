package dev.hmh.ammmvvm.data.repository

import dev.hmh.ammmvvm.base.BaseApiResponse
import dev.hmh.ammmvvm.data.model.LoginResponse
import dev.hmh.ammmvvm.data.network.remote.RemoteDataSource
import dev.hmh.ammmvvm.util.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepository
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun addUser(
        userName: String,
        email: String,
        password: String
    ): Flow<ApiResponse<LoginResponse>> {
        delay(2000L)
        return flow<ApiResponse<LoginResponse>> {
            emit(safeApiCall { remoteDataSource.addUser(userName, email, password) })
        }.flowOn(Dispatchers.IO)
    }


}