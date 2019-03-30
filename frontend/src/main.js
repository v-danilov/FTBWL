// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import { store } from './store/store'
import axios from 'axios'

axios.defaults.baseURL = '/api'
Vue.prototype.$http = axios
// lodash
Vue.use(Vuetify, {
  theme: {
    primary: '#7cb342', // Primary system color
    secondary: '#348C5D', // Secondary system color
    accent: '#C66549', // Accent color for elements to pay attention
    additional: '#AC4068' // Extra color
  }
  // If you are not designer: https://colorscheme.ru/#2x41TkdoRvymD
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
