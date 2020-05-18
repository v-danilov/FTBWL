// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import VueCookies from 'vue-cookies'

import {store} from '@/store/store.js'
import axios from 'axios'
import {COLORS} from './components/util/constants/ColorsConstants'
import {HTTPResponseStatusConstants} from './components/util/constants/CommonConstants'
import UserCookiesClass from './store/cookie/UserCookiesClass'
import {NOTIFICATION_TYPES} from './components/notifications/notificationTypes'

axios.defaults.baseURL = '/api' + '/v0' // TODO read from config
// axios.defaults.timeout = 2500
Vue.prototype.$http = axios

const vuetifyOpts = {
  theme: {
    dark: false,
    themes: {
      light: {
        primary: COLORS.PRIMARY, // Primary system color
        secondary: COLORS.SECONDARY, // Secondary system color
        accent: COLORS.ACCENT, // Accent color for elements to pay attention
        additional: COLORS.ADDITIONAL, // Extra color
        background: COLORS.BACKROUND
      }
    }
    // If you are not designer: https://colorscheme.ru/#2x41TkdoRvymD
  }
}
Vue.use(Vuetify)
Vue.use(VueCookies)
Vue.config.productionTip = false

axios.interceptors.request.use(function (request) {
  const tokenFromCookies = UserCookiesClass.getToken()
  if (tokenFromCookies !== null && tokenFromCookies !== '') {
    request.headers.Authorization = 'Bearer ' + tokenFromCookies
  }
  return request
}, function (error) {
  return Promise.reject(error)
})

axios.interceptors.response.use(function (response) {
  return response
}, function (error) {
  const currentRoutePath = router.currentRoute.path
  // '/login' added as workaround to resolve the redirecting bug from /login to /login in case of 403
  console.log('main')
  console.log(error)
  if (error.response.status === HTTPResponseStatusConstants.FORBIDDEN && currentRoutePath !== '/login') {
    store.dispatch('saveRouteToJump', currentRoutePath)
    UserCookiesClass.setToken('') // reset token to complete dictionary downloading request
    UserCookiesClass.setAutheticatedUser(null) // reset user data
    store.dispatch('notifications/add', {
      type: NOTIFICATION_TYPES.INFO,
      text: 'Please, login first'
    })
    router.push('/login')
  }
  return Promise.reject(error)
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  vuetify: new Vuetify(vuetifyOpts),
  components: {
    App
  },
  template: '<App/>'
}).$mount('#app')
