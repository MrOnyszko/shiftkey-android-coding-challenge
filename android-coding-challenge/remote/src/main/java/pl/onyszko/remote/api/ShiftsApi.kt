package pl.onyszko.remote.api

import pl.onyszko.remote.model.Envelope
import pl.onyszko.remote.model.RemoteGeoCoordinates
import pl.onyszko.remote.model.RemoteShiftGroup
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import java.time.OffsetDateTime

interface ShiftsApi {
  @GET("/api/v2/available_shifts")
  suspend fun fetchAvailableShifts(
    @Query("type") type: String,
    @Query("start") start: LocalDate,
    @Query("end") end: LocalDate,
    @Query("address", encoded = true) address: String,
    @Query("radius") radius: Int,
  ): Envelope<List<RemoteShiftGroup>, RemoteGeoCoordinates>
}