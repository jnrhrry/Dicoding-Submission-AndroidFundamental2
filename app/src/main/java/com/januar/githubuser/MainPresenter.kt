package com.januar.githubuser

import com.januar.githubuser.adapter.Search
import com.januar.githubuser.adapter.Users
import com.januar.githubuser.detailsapi.GithubAPI
import com.januar.githubuser.detailsapi.GithubAPIClient
import com.januar.githubuser.mvpsupport.MainView
import com.januar.githubuser.mvpsupport.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainPresenter: Presenter<MainView> {
    private var mView: MainView?=null
    private lateinit var githubAPI: GithubAPI

    override fun onAttach(view: MainView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun loadData(){
        githubAPI = GithubAPIClient.providesHttpAdapter().create(GithubAPI::class.java)
        mView?.onShowLoading()
        githubAPI.getUserList().enqueue(object :Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                val result = response.body() ?: emptyList()
                mView?.onSuccess(result)
                mView?.onHideLoading()
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                mView?.onError(t.toString())
                mView?.onHideLoading()
            }
        })
    }



    fun getUserDetails(username: String){
        githubAPI.getDetailUser(username).enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val result = response.body()
                val user = Users(
                    result?.username ?: "",
                    result?.name ?: "",
                    result?.avatar ?: "",
                    result?.company ?: "",
                    result?.location ?: "",
                    result?.repository ?: "",
                    result?.followers ?: "",
                    result?.following ?: "",
                    result?.html_url ?: ""
                )
                mView?.onDetailSuccess(user)
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                mView?.onError(t.toString())
            }
        })
    }

    fun searchUserData(user: String){
        mView?.onShowLoading()
        githubAPI.searchUser(user).enqueue(object : Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                val result = response.body()!!.listOfUser
                mView?.onSuccess(result)
                mView?.onHideLoading()
            }
            override fun onFailure(call: Call<Search>, t: Throwable) {
                mView?.onHideLoading()
                mView?.onError(t.toString())
            }
        })
    }
}