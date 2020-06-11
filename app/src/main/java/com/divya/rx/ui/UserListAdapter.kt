package com.divya.rx.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.divya.rx.R
import com.divya.rx.databinding.ItemUserListBinding
import com.divya.rx.datalayer.model.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    var mValues: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemArticleBinding = DataBindingUtil.inflate<ItemUserListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user_list,
            parent,
            false
        )
        return ViewHolder(itemArticleBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = mValues[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mBinding: ItemUserListBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(user: User) {
            mBinding.user = user
        }
    }
}
