package io.github.importre.rfp.api.repo

import com.google.gson.annotations.SerializedName
import io.github.importre.rfp.api.owner.Owner

data class Repository(val id: Int,

                      val name: String,

                      @SerializedName("full_name")
                      val fullName: String,

                      val owner: Owner,

                      val language: String,

                      @SerializedName("stargazers_count")
                      val stargazersCount: Long,

                      @SerializedName("watchers_count")
                      val watchersCount: Long,

                      @SerializedName("forks_count")
                      val forksCount: Long) : Comparable<Repository> {

    override fun compareTo(other: Repository): Int {
        return (other.stargazersCount - this.stargazersCount).toInt()
    }
}
