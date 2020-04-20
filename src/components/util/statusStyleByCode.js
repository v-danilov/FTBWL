import { COLORS } from './constants/ColorsConstants'
import { EVENT_STATUS_CODE } from './constants/EventStatusNames'
import {
  mdiPauseCircle, mdiCalendarCheck,
  mdiAccountClock, mdiAccountPlus, mdiAccountLock,
  mdiCards, mdiCalendarRemove, mdiCalendarClock
} from '@mdi/js'

/* exported statusColorize */
export default function statusStyleByCode (statusCode) {
  switch (statusCode) {
    case EVENT_STATUS_CODE.REG_WAIT:
      return { icon: mdiAccountClock, color: COLORS.ACCENT }
    case EVENT_STATUS_CODE.REG_ON:
      return { icon: mdiAccountPlus, color: COLORS.SECONDARY }
    case EVENT_STATUS_CODE.REG_OFF:
      return { icon: mdiAccountLock, color: COLORS.ACCENT }
    case EVENT_STATUS_CODE.START:
      return { icon: mdiCards, color: COLORS.SECONDARY }
    case EVENT_STATUS_CODE.CANCEL:
      return { icon: mdiCalendarRemove, color: COLORS.ADDITIONAL }
    case EVENT_STATUS_CODE.END:
      return { icon: mdiCalendarCheck, color: COLORS.PRIMARY }
    case EVENT_STATUS_CODE.PAUSE:
      return { icon: mdiPauseCircle, color: COLORS.ADDITIONAL }
    case EVENT_STATUS_CODE.SCHEDULED:
      return { icon: mdiCalendarClock, color: COLORS.ACCENT }
    default:
      console.log('WARNING: не опознан цвет для статуса ' + statusCode)
      return { icon: 'help', color: COLORS.PRIMARY }
  }
}
