package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName

data class Envelope<T, E>(
  @SerializedName("data") val data: T,
  @SerializedName("meta") val meta: E,
)