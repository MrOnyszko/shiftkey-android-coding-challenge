package pl.onyszko.remote.mapper

import pl.onyszko.domain.model.Shift
import pl.onyszko.domain.model.ShiftGroup
import pl.onyszko.remote.model.RemoteGeoCoordinates
import pl.onyszko.remote.model.RemoteShiftGroup
import javax.inject.Inject

class RemoteShiftGroupToShiftGroupMapper @Inject constructor() :
  ArgMapper<RemoteShiftGroup, ShiftGroup, RemoteGeoCoordinates> {
  override fun map(
    value: RemoteShiftGroup,
    arg: RemoteGeoCoordinates
  ): ShiftGroup {
    return ShiftGroup(
      lat = arg.lat,
      lng = arg.lng,
      date = value.date,
      shifts = value.shifts.map { element ->
        Shift(
          shiftId = element.shiftId,
          startTime = element.startTime,
          endTime = element.endTime,
          timezone = element.timezone,
          premiumRate = element.premiumRate,
          covid = element.covid,
          shiftKind = element.shiftKind,
          withinDistance = element.withinDistance,
          facilityName = element.facilityType.name,
          skillName = element.skill.name,
          localizedSpecialtyName = element.localizedSpecialty.name,
          localizedSpecialtyAbbreviation = element.localizedSpecialty.abbreviation,
        )
      }
    )
  }
}