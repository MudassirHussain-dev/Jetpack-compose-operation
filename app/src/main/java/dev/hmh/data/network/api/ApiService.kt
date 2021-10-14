package dev.hmh.ammmvvm.data.network.api

import dev.hmh.ammmvvm.data.model.LoginResponse
import dev.hmh.ammmvvm.util.Constants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(Constants.ADD_USER)
    suspend fun addUser(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
    ): Response<LoginResponse>

}