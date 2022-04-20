package pl.onyszko.remote.model

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class RemoteShift(
  @SerializedName("shift_id") val shiftId: Int,
  @SerializedName("start_time") val startTime: OffsetDateTime,
  @SerializedName("end_time") val endTime: OffsetDateTime,
  @SerializedName("normalized_start_date_time") val normalizedStartDateTime: String,
  @SerializedName("normalized_end_date_time") val normalizedEndDateTime: String,
  @SerializedName("timezone") val timezone: String,
  @SerializedName("premium_rate") val premiumRate: Boolean,
  @SerializedName("covid") val covid: Boolean,
  @SerializedName("shift_kind") val shiftKind: String,
  @SerializedName("within_distance") val withinDistance: Int?,
  @SerializedName("facility_type") val facilityType: RemoteFacilityType,
  @SerializedName("skill") val skill: RemoteSkill,
  @SerializedName("localized_specialty") val localizedSpecialty: RemoteLocalizedSpecialty
)