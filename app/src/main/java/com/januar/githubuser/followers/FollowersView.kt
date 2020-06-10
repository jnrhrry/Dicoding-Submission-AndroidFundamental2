package com.januar.githubuser.followers

import com.januar.githubuser.adapter.Users
import com.januar.githubuser.mvpsupport.View

interface FollowersView: View {
    fun onShowLoading()
    fun onHideLoading()
    fun onSuccess(result: List<Users>)
    fun onError(error: String)
}