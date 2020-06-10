package com.januar.githubuser.mvpsupport

import com.januar.githubuser.adapter.Users

interface MainView : View {
    fun onSuccess(result: List<Users>)
    fun onError(error: String)
    fun onDetailSuccess(user: Users)
    fun onShowLoading()
    fun onHideLoading()
}
