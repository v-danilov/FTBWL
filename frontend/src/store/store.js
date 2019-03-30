import Vue from 'vue'
import Vuex from 'vuex'
import authorizationModule from './modules/authorizationModule'
import filterCacheModule from './modules/filterCacheModule'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {},
  getters: {},

  // this.$store.commit(“SET_USER”, user) synchronous
  mutations: {},

  // this.$store.dispatch("SET_USER",user) asynchronous
  actions: {},
  modules: {
    authorizationModule,
    filterCacheModule
  }
})
