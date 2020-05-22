import UserCookiesClass from '@/store/cookie/UserCookiesClass'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'
import {store} from '@/store/store.js'
import router from '@/router'

export default class SecurityModule {
  static doActionIfUserIsAuthenticated (actionFunc, vm) {
    const userData = UserCookiesClass.getAutheticatedUser()
    if (userData == null) {
      store.dispatch('notifications/add', {
        type: NOTIFICATION_TYPES.INFO,
        text: 'Please, login to create an event'
      })
      router.push('/login')
    } else {
      actionFunc(vm)
    }
  }
}
