package com.giuseppesorce.watchersexplorer.android.ui.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.giuseppesorce.common.transformationimage.CircleTransformBorder
import com.giuseppesorce.commonui.bindView
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * @author Giuseppe Sorce
 */
class RepoListAdapter(repoList: List<Repo> = emptyList()) : RecyclerView.Adapter<RepoListAdapter.MyViewHolder>() {


    override fun getItemCount(): Int = allRepos.size

    var onActionClickListener: ((action: Repo, position: Int) -> Unit)? = null

    var allRepos by Delegates.observable(repoList) { _, _, _ -> notifyDataSetChanged() }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var view = holder.bindItems(allRepos[position])
        view.setOnClickListener {
            onActionClickListener?.invoke(allRepos[position], position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(
            R.layout.itemlist_repocard,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    fun clear() {
        allRepos= emptyList()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivAvatar: ImageView by bindView(R.id.ivAvatar)
        val tvRepoName: TextView by bindView(R.id.tvRepoName)
        val tvOwnerName: TextView by bindView(R.id.tvOwnerName)
        val tvDescription: TextView by bindView(R.id.tvDescription)
        val tvCount: TextView by bindView(R.id.tvCount)
        val tvWatcher: TextView by bindView(R.id.tvWatcher)
        fun bindItems(repo: Repo): View {
            Picasso.get().load(repo.avatar_url).placeholder(R.drawable.ic_github_placeholder).resize(300,300).transform(CircleTransformBorder(Color.parseColor("#d8d8d8"))).into(ivAvatar)
            tvRepoName.text = repo.name
            tvOwnerName.text = repo.nameOwner
            tvDescription.text = repo.description
            tvCount.text = repo.forks_count.toString()
            tvWatcher.text = repo.watchers_count.toString()
            return itemView
        }
    }

}