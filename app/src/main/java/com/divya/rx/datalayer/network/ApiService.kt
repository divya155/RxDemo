package com.divya.rx.datalayer.network

import com.divya.rx.Constants
import com.divya.rx.datalayer.model.BaseResponse
import com.divya.rx.datalayer.model.Resource
import com.divya.rx.datalayer.model.User
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users?_format=json&access-token=${Constants.ACCESS_TOKEN}")
    fun getUsers(@Query("first_name") username: String): Observable<Resource<BaseResponse<List<User>>>>?

    @GET("users?_format=json&access-token=${Constants.ACCESS_TOKEN}")
    fun searchUsers(@Query("first_name") username: String): Resource<BaseResponse<List<User>>>

    @GET("users?_format=json&access-token=${Constants.ACCESS_TOKEN}")
    fun searchUser(@Query("first_name") username: String): Flowable<BaseResponse<List<User>>>

}