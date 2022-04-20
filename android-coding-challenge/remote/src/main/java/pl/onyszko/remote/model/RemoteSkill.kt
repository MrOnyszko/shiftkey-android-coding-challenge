package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteSkill(
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String,
  @SerializedName("color") val color: String
)