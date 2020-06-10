package com.januar.githubuser.detailsapi

import com.januar.githubuser.adapter.Search
import com.januar.githubuser.adapter.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {
    @GET("users")
    @Headers("Authorization: token 888d4c00c00c22d11621d3ba05d945c541a30996")
    fun getUserList() : Call<List<Users>>

    @GET("users/{username}")
    @Headers("Authorization: token 888d4c00c00c22d11621d3ba05d945c541a30996")
    fun getDetailUser(@Path("username") username : String) : Call<Users>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 888d4c00c00c22d11621d3ba05d945c541a30996")
    fun getFollowerList(@Path("username") username : String) : Call<List<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 888d4c00c00c22d11621d3ba05d945c541a30996")
    fun getFollowingList(@Path("username") username : String) : Call<List<Users>>

    @GET("search/users")
    @Headers("Authorization: token 888d4c00c00c22d11621d3ba05d945c541a30996")
    fun searchUser(@Query("q") q : String) : Call<Search>
}