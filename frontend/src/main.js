// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import colors from 'vuetify/es5/util/colors'
import Vuex from 'vuex'
import VueCookie from 'vue-cookie'
import VueLodash from 'vue-lodash'
import axios from 'axios'
import jsonp from 'vue-jsonp'
import VueAxios from 'vue-axios'

Vue.use(VueLodash)
Vue.use(VueAxios, axios)
Vue.use(jsonp)
Vue.use(Vuetify, {
  theme: {
    primary: '#7cb342', // #7cb342
    secondary: colors.red.lighten4, // #FFCDD2
    accent: colors.indigo.base // #3F51B5
  }
})

Vue.use(VueCookie) // https://www.npmjs.com/package/vue-cookie
Vue.use(Vuex)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
