import {HTTPResponseStatusConstants} from '../../components/util/constants/CommonConstants'
import {END_POINTS} from '../../components/util/constants/EndPointsConstants'
import Axios from 'axios'
/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  user: localStorage.getItem('user') || null,
  status: ''
}
const getters = {
  isAuthenticated: state => !!state.user,
  authStatus: state => state.status
}
// this.$store.commit(“SET_USER”, user) synchronous
const mutations = {
  SET_USER: (state, payload) => {
    state.user = payload
    state.status = 'authorized'
  }
}
// this.$store.dispatch("SET_USER",user) asynchronous

const actions = {
  SIGNIN_ACTION: async (context, payload) => {
    await Axios.post(END_POINTS.AUTHORIZATION.SIGN_IN, payload)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          context.commit('SET_USER', payload)
        }
      })
      .catch(response => {
        console.log('Authorization failed')
      })
  }
}

export default {
  state, getters, mutations, actions
}
