package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteLocalizedSpecialty(
  @SerializedName("id") val id: Int,
  @SerializedName("specialty_id") val specialtyId: Int,
  @SerializedName("state_id") val stateId: Int,
  @SerializedName("name") val name: String,
  @SerializedName("abbreviation") val abbreviation: String,
  @SerializedName("specialty") val specialty: RemoteSpecialty
)