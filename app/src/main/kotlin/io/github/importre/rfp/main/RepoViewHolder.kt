package io.github.importre.rfp.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import io.github.importre.rfp.api.repo.Repository
import io.github.importre.rfp.ext.join
import io.github.importre.rfp.ext.loadUrl
import kotlinx.android.synthetic.main.layout_repo_card.view.*

class RepoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setRepo(repo: Repository) {
        itemView.run {
            fg.setOnClickListener {
                val message = repo.run {
                    "\n".join(fullName, language)
                }
                Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
            }
            ownerImage.loadUrl(repo.owner.avatarUrl)
            name.text = repo.name
            star.text = "${repo.stargazersCount}"
            fork.text = "${repo.forksCount}"
        }
    }
}
