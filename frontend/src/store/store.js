import Vue from 'vue'
import Vuex from 'vuex'
import filterCacheModule from './modules/filterCacheModule'
import commonModule from './modules/commonModule'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {},
  getters: {},

  // this.$store.commit(“SET_USER”, user) synchronous
  mutations: {},

  // this.$store.dispatch("SET_USER",user) asynchronous
  actions: {},
  modules: {
    filterCacheModule,
    commonModule
  }
})
