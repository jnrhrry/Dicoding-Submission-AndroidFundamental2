package com.januar.githubuser.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.januar.githubuser.R
import com.januar.githubuser.followers.FollowersFragment
import com.januar.githubuser.following.FollowingFragment

class SectionsPagerAdapter(private val mContext:Context, fm: FragmentManager, private val username: String):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val TAB_TITLES = intArrayOf(
        R.string.followingTab,
        R.string.followersTab
    )
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment?=null
        when(position){
            0 -> {fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(FollowingFragment.EXTRA_USERNAME, username)
            fragment.arguments = bundle
            }
            1 -> {fragment = FollowersFragment()
            val bundle = Bundle()
            bundle.putString(FollowersFragment.EXTRA_USERNAME, username)
            fragment.arguments = bundle
            }
        }
    return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

}