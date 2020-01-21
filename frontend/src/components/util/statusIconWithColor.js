import { COLORS } from './constants/ColorsConstants'
import {EVENT_STATUS_CODE} from './constants/EventStatusNames'
/* exported statusColorize */
export default function statusColorize (status) {
  switch (status) {
    case EVENT_STATUS_CODE.SCHEDULED:
      return {icon: 'query_builder', color: COLORS.ADDITIONAL}
    case EVENT_STATUS_CODE.REGISTRATION_OPEN:
      return {icon: 'done', color: COLORS.SECONDARY}
    case EVENT_STATUS_CODE.CLOSED:
      return {icon: 'done_all', color: COLORS.ACCENT}
    default:
      console.log('WARNING: не опознан цвет для статуса ' + status)
      return {icon: 'help', color: COLORS.PRIMARY}
  }
}
