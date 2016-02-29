package io.github.importre.kdr.api.owner

import com.google.gson.annotations.SerializedName

data class Owner(val login: String,

                 val id: Long,

                 @SerializedName("avatar_url")
                 val avatarUrl: String)
