// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import { store } from './store/store'
import axios from 'axios'
import { COLORS } from './components/util/constants/ColorsConstants'

axios.defaults.baseURL = '/api'
Vue.prototype.$http = axios

const vuetifyOpts = {
  theme: {
    dark: false,
    themes: {
      light: {
        primary: COLORS.PRIMARY, // Primary system color
        secondary: COLORS.SECONDARY, // Secondary system color
        accent: COLORS.ACCENT, // Accent color for elements to pay attention
        additional: COLORS.ADDITIONAL // Extra color
      }
    }
    // If you are not designer: https://colorscheme.ru/#2x41TkdoRvymD
  }
}
Vue.use(Vuetify)
let VueCookie = require('vue-cookie')
Vue.use(VueCookie)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  vuetify: new Vuetify(vuetifyOpts),
  components: { App },
  template: '<App/>'
}).$mount('#app')
