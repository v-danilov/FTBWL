/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  firstPairingPlayer: localStorage.getItem('firstPairingPlayer') || {},
  secondPairingPlayer: localStorage.getItem('secondPairingPlayer') || {}
}
const getters = {
  firstPairingPlayer: state => state.firstPairingPlayer,
  secondPairingPlayer: state => state.secondPairingPlayer
}

// this.$store.commit(“SET_USER”, user) synchronous
const mutations = {
  SET_FIRST_PAIRING_PLAYER: (state, payload) => {
    state.firstPairingPlayer = payload
  },
  SET_SECOND_PAIRING_PLAYER: (state, payload) => {
    state.secondPairingPlayer = payload
  }
}

// this.$store.dispatch("SET_USER",user) asynchronous
const actions = {
  INIT_FIRST_PAIRING_PLAYER: (context, data) => {
    context.commit('SET_FIRST_PAIRING_PLAYER', data)
  },
  INIT_SECOND_PAIRING_PLAYER: (context, data) => {
    context.commit('SET_SECOND_PAIRING_PLAYER', data)
  }
}

export default {
  state, getters, mutations, actions
}
