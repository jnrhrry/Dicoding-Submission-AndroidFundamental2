package com.januar.githubuser.following

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.januar.githubuser.R
import com.januar.githubuser.adapter.UserAdapter
import com.januar.githubuser.adapter.Users
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment(), FollowingView {

    companion object {
        const val EXTRA_USERNAME = "username"
    }
    private lateinit var adapter: UserAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: FollowingPresenter
    private var username: String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(EXTRA_USERNAME) ?: ""
        rvUserFollowing.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        progressBar = view.findViewById(R.id.progressBarFollowing)
        presenter = FollowingPresenter()
        onAttachView()

    }

    override fun onShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onSuccess(result: List<Users>) {
        if (result.isEmpty()){
            tvNoFollowing.visibility = View.VISIBLE
        }
        adapter = UserAdapter(result)
        rvUserFollowing.adapter = adapter
    }

    override fun onError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
        Log.d("There is an error: ", error)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
        presenter.getFollowingData(username?:"")
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

}
