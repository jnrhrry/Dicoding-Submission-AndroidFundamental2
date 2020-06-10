package com.januar.githubuser.adapter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    @SerializedName("login")
    var username: String,
    var name: String,
    @SerializedName("avatar_url")
    var avatar: String,
    var company: String,
    var location: String,
    @SerializedName("public_repos")
    var repository: String,
    var followers: String,
    var following: String,
    var html_url: String
): Parcelable

data class Search(
    @SerializedName("items")
    var listOfUser: List<Users>
)