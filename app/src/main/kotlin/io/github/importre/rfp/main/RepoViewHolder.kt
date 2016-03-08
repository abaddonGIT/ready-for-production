package io.github.importre.rfp.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import io.github.importre.rfp.R
import io.github.importre.rfp.api.repo.Repository
import io.github.importre.rfp.ext.getTagAsImageView
import io.github.importre.rfp.ext.getTagAsTextView
import io.github.importre.rfp.ext.join
import io.github.importre.rfp.ext.loadUrl

class RepoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setRepo(repo: Repository) {
        view.run {
            findViewById(R.id.fg).setOnClickListener {
                val message = repo.run {
                    "\n".join(fullName, language)
                }
                Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
            }

            getTagAsImageView(R.id.ownerImage).loadUrl(repo.owner.avatarUrl)
            getTagAsTextView(R.id.name).text = repo.name
            getTagAsTextView(R.id.star).text = "${repo.stargazersCount}"
            getTagAsTextView(R.id.fork).text = "${repo.forksCount}"
        }
    }
}
