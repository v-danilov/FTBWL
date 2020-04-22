import { mdiCheckCircleOutline, mdiInformationOutline, mdiAlertCircleOutline } from '@mdi/js'

export const NOTIFICATION_TYPES = {
  INFO: {color: 'info', icon: mdiInformationOutline},
  ERROR: {color: 'error', icon: mdiAlertCircleOutline},
  SUCCESS: {color: 'success', icon: mdiCheckCircleOutline}
}
