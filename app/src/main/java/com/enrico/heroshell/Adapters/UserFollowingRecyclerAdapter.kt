package com.enrico.heroshell.Adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.enrico.heroshell.Models.User
import com.enrico.heroshell.RecyclerViewCells.UserCell

/**
 * Created by enrico on 10/19/17.
 */
class UserFollowingRecyclerAdapter(val users: ArrayList<User>): RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder?.itemView as? UserCell)?.let { view ->
            view.user = users[position]
            view.setup()
        }
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(UserCell())
}