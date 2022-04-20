package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteSpecialty(
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String,
  @SerializedName("color") val color: String,
  @SerializedName("abbreviation") val abbreviation: String
)