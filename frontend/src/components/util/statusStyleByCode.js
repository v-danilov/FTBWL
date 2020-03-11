import { COLORS } from './constants/ColorsConstants'
import {EVENT_STATUS_CODE} from './constants/EventStatusNames'
import { mdiPlayCircle, mdiPauseCircle, mdiCloseCircle,
  mdiAccountClock, mdiAccountPlus, mdiAccountLock,
  mdiCards, mdiArchive, mdiClockFast} from '@mdi/js'

/* exported statusColorize */
export default function statusStyleByCode (statusCode) {
  switch (statusCode) {
    case EVENT_STATUS_CODE.REG_WAIT:
      return {icon: mdiAccountClock, color: COLORS.ACCENT}
    case EVENT_STATUS_CODE.REG_ON:
      return {icon: mdiAccountPlus, color: COLORS.SECONDARY}
    case EVENT_STATUS_CODE.REG_OFF:
      return {icon: mdiAccountLock, color: COLORS.ACCENT}
    case EVENT_STATUS_CODE.START:
      return {icon: mdiPlayCircle, color: COLORS.SECONDARY}
    case EVENT_STATUS_CODE.CANCEL:
      return {icon: mdiCloseCircle, color: COLORS.ADDITIONAL}
    case EVENT_STATUS_CODE.PLAY:
      return {icon: mdiCards, color: COLORS.SECONDARY}
    case EVENT_STATUS_CODE.END:
      return {icon: mdiArchive, color: COLORS.PRIMARY}
    case EVENT_STATUS_CODE.PAUSE:
      return {icon: mdiPauseCircle, color: COLORS.ADDITIONAL}
    case EVENT_STATUS_CODE.SCHEDULED:
      return {icon: mdiClockFast, color: COLORS.ACCENT}
    default:
      console.log('WARNING: не опознан цвет для статуса ' + statusCode)
      return {icon: 'help', color: COLORS.PRIMARY}
  }
}
