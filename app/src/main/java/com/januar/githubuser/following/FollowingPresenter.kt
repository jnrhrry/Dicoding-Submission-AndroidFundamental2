package com.januar.githubuser.following

import com.januar.githubuser.adapter.Users
import com.januar.githubuser.detailsapi.GithubAPI
import com.januar.githubuser.detailsapi.GithubAPIClient
import com.januar.githubuser.mvpsupport.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingPresenter : Presenter<FollowingView>{
    private var mView:FollowingView?=null

    override fun onAttach(view: FollowingView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getFollowingData(username: String){
        val githubAPIInterface: GithubAPI = GithubAPIClient.providesHttpAdapter().create(GithubAPI::class.java)
        mView?.onShowLoading()
        githubAPIInterface.getFollowingList(username).enqueue(object: Callback<List<Users>>{
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