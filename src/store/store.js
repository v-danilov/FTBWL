import Vue from 'vue'
import Vuex from 'vuex'
import dictCacheModule from './modules/dictCacheModule'
import pairingPlayersModule from './modules/pairingPlayersModule'
import commonModule from './modules/commonModule'
import notifications from './modules/notifications'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {},
  getters: {},

  // this.$store.commit(“SET_USER”, user) synchronous
  mutations: {},

  // this.$store.dispatch("SET_USER",user) asynchronous
  actions: {},
  modules: {
    dictCacheModule,
    pairingPlayersModule,
    commonModule,
    notifications
  }
})
