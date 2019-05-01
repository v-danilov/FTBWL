import { COLORS } from './constants/ColorsConstants'
/* exported statusColorize */
export default function statusColorize (status) {
  if (status === 'Запланирован') {
    return {icon: 'query_builder', color: COLORS.ADDITIONAL}
  }
  if (status === 'Открыта регистрация') {
    return {icon: 'done', color: COLORS.SECONDARY}
  }
  if (status === 'Окончен' || status === 'Завершен') {
    return {icon: 'done_all', color: COLORS.ACCENT}
  }
}
