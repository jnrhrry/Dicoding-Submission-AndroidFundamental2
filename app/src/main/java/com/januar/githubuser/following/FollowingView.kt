package com.januar.githubuser.following

import com.januar.githubuser.adapter.Users
import com.januar.githubuser.mvpsupport.View

interface FollowingView : View {
    fun onShowLoading()
    fun onHideLoading()
    fun onSuccess(result: List<Users>)
    fun onError(error: String)
}