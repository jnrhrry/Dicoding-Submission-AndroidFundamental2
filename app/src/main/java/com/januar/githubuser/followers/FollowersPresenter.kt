package com.januar.githubuser.followers

import com.januar.githubuser.adapter.Users
import com.januar.githubuser.detailsapi.GithubAPI
import com.januar.githubuser.detailsapi.GithubAPIClient
import com.januar.githubuser.mvpsupport.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersPresenter: Presenter<FollowersView>{
    private var mView:FollowersView?=null

    override fun onAttach(view: FollowersView) {
        mView = view
    }

    override fun onDetach() {
        mView= null
    }

    fun getFollowersData(username: String){
        val githubAPI: GithubAPI = GithubAPIClient.providesHttpAdapter().create(GithubAPI::class.java)
        mView?.onShowLoading()
        githubAPI.getFollowerList(username).enqueue(object :Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                val result = response.body() ?: emptyList()
                mView?.onHideLoading()
                mView?.onSuccess(result)
            }
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                mView?.onHideLoading()
                mView?.onError(t.toString())
            }
        })
    }

}