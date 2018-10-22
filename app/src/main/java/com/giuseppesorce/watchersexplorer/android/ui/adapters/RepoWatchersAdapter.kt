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
import com.giuseppesorce.watchersexplorer.data.api.models.RepoWatcher
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * @author Giuseppe Sorce
 */
class RepoWatchersAdapter(repoList: List<RepoWatcher> = emptyList()) : RecyclerView.Adapter<RepoWatchersAdapter.MyViewHolder>() {


    override fun getItemCount(): Int = allWatchers.size

    var onActionClickListener: ((action: RepoWatcher, position: Int) -> Unit)? = null

    var allWatchers by Delegates.observable(repoList) { _, _, _ -> notifyDataSetChanged() }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var view = holder.bindItems(allWatchers[position])
        view.setOnClickListener {
            onActionClickListener?.invoke(allWatchers[position], position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(
            R.layout.itemlist_repowatcher,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    fun clear() {
        allWatchers= emptyList()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivAvatar: ImageView by bindView(R.id.ivAvatar)
        val tvWatcherName: TextView by bindView(R.id.tvWatcherName)

        fun bindItems(watcher: RepoWatcher): View {
            Picasso.get().load(watcher.avatar_url).resize(300,300).transform(CircleTransformBorder(Color.parseColor("#d8d8d8"))).into(ivAvatar)
            tvWatcherName.text = watcher.login
            return itemView
        }
    }

}