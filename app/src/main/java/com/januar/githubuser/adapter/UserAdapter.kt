package com.januar.githubuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.januar.githubuser.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_user.view.*

class UserAdapter internal constructor(private val users: List<Users>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_user, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val picasso: Picasso = Picasso.get()
        fun bind(user: Users){
            with(itemView){
                picasso
                    .load(user.avatar)
                    .resize(300,300)
                    .into(ivPhoto)
                tvName.text = user.username
                tvUrl.text = resources.getString(R.string.url, user.html_url)

                itemView.setOnClickListener{onItemClickCallback?.onItemClicked(user)}
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }

}