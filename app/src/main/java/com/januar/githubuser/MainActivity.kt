package com.januar.githubuser

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.januar.githubuser.adapter.UserAdapter
import com.januar.githubuser.adapter.Users
import com.januar.githubuser.mvpsupport.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),
    MainView {

    private lateinit var adapter: UserAdapter
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter=MainPresenter()
        rvUser.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        onAttachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu!!.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.searchUser)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText != ""){
                    presenter.searchUserData(newText)
                } else {presenter.loadData()
                }
                return true
                }
        })

        MenuItemCompat.setOnActionExpandListener(searchItem, object: MenuItemCompat.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.loadData()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
        }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.changeLanguage){
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override  fun onSuccess(result: List<Users>){
        adapter = UserAdapter(result)
        rvUser.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object: UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Users) {
                presenter.getUserDetails(data.username)
            }

        })
    }
    override fun onError(error: String){
        Log.d(MainActivity::class.java.simpleName, error)
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
    override fun onDetailSuccess(user: Users){
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.EXTRA_USER, user)
        startActivity(intent)
    }
    override fun onShowLoading(){
        progressBar.visibility = View.VISIBLE
    }
    override fun onHideLoading(){
        progressBar.visibility = View.GONE
    }
    override fun onAttachView(){
        presenter.onAttach(this)
        presenter.loadData()
    }

    override fun onDetachView() {
        presenter.onDetach()
        presenter.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }
}
