package com.januar.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.januar.githubuser.adapter.SectionsPagerAdapter
import com.januar.githubuser.adapter.Users
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var sectionPageAdapter: SectionsPagerAdapter
    private var toUser:Users?=null

    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.getParcelableExtra(EXTRA_USER) as Users
        toUser = intent.getParcelableExtra(EXTRA_USER)
        supportActionBar?.title = toUser?.username

        setAdapter(user)
        userDetailsData(user)
    }

    private fun setAdapter(user: Users){
        sectionPageAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager,
            user.username
        )
        view_pager.adapter = sectionPageAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }

    private fun userDetailsData(user: Users){
        //tvNameDetails.text = user.name
        tvUsernameDetails.text = user.name
        tvCompany.text = user.company
        tvLocation.text = user.location
        tvRepo.text = user.repository
        tvFollowers.text = user.followers
        tvFollowing.text = user.following
        Picasso.get()
            .load(user.avatar)
            .resize(300,300)
            .into(ivPhotoUserDetail)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
