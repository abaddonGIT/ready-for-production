package io.github.importre.rfp.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.importre.rfp.R
import io.github.importre.rfp.api.repo.Repository

class RepoAdapter(private val context: Context) : RecyclerView.Adapter<RepoViewHolder>() {

    val repos = arrayListOf<Repository>()

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder?, position: Int) {
        repos.getOrNull(position)?.let {
            holder?.setRepo(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepoViewHolder? {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_repo_card, parent, false).apply {
            setTag(R.id.name, findViewById(R.id.name))
            setTag(R.id.star, findViewById(R.id.star))
            setTag(R.id.fork, findViewById(R.id.fork))
            setTag(R.id.ownerImage, findViewById(R.id.ownerImage))
        }
        return RepoViewHolder(view)
    }
}