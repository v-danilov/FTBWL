import { COLORS } from './constants/ColorsConstants'
import {TOURNAMENT_SYSTEM_NAMES} from './constants/TournamentStatusNames'
/* exported statusColorize */
export default function statusColorize (status) {
  switch (status) {
    case TOURNAMENT_SYSTEM_NAMES.SCHEDULED:
      return {icon: 'query_builder', color: COLORS.ADDITIONAL}
    case TOURNAMENT_SYSTEM_NAMES.REGISTRATION_OPEN:
      return {icon: 'done', color: COLORS.SECONDARY}
    case TOURNAMENT_SYSTEM_NAMES.CLOSED:
      return {icon: 'done_all', color: COLORS.ACCENT}
    default:
      console.log('WARNING: не опознан цвет для статуса ' + status)
  }
}
