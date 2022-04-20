package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class RemoteGeoCoordinates(
  @SerializedName("lat") val lat: BigDecimal?,
  @SerializedName("lng") val lng: BigDecimal?
)
