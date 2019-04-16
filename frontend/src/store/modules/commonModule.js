import {HTTPResponseStatusConstants} from '../../components/util/constants/CommonConstants'
import Axios from 'axios'

/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  fractions: localStorage.getItem('fractions') || []
}
const getters = {
  cachedFractions: state => state.fractions
}

// this.$store.commit(“SET_USER”, user) synchronous
const mutations = {
  SET_FRACTIONS: (state, payload) => {
    state.fractions = payload
  }
}

// this.$store.dispatch("SET_USER",user) asynchronous
const actions = {
  INIT_FRACTIONS: (context, url) => {
    Axios.get(url).then(response => {
      if (response.status === HTTPResponseStatusConstants.OK) {
        context.commit('SET_FRACTIONS', response.data)
      }
    })
      .catch(reason => {
        console.log('URL is ' + url)
        console.log('Failed to cache fractions 🤷‍♂️')
      })
  }
}

export default {
  state, getters, mutations, actions
}
