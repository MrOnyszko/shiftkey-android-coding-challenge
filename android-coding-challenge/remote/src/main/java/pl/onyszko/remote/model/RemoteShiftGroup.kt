package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.OffsetDateTime

data class RemoteShiftGroup(
  @SerializedName("date") val date: LocalDate,
  @SerializedName("shifts") val shifts: List<RemoteShift>
)