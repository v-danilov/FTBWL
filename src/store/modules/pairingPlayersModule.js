/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  pairingPlayerLeft: localStorage.getItem('pairingPlayerLeft') || {},
  pairingPlayerRight: localStorage.getItem('pairingPlayerRight') || {}
}
const getters = {
  pairingPlayerLeft: state => state.pairingPlayerLeft,
  pairingPlayerRight: state => state.pairingPlayerRight
}

// this.$store.commit(“SET_USER”, user) synchronous
const mutations = {
  SET_LEFT_PAIRING_PLAYER: (state, payload) => {
    state.pairingPlayerLeft = payload
  },
  SET_RIGHT_PAIRING_PLAYER: (state, payload) => {
    state.pairingPlayerRight = payload
  }
}

// this.$store.dispatch("SET_USER",user) asynchronous
const actions = {
  setPairingPlayerLeft: (context, data) => {
    context.commit('SET_LEFT_PAIRING_PLAYER', data)
  },
  setPairingPlayerRight: (context, data) => {
    context.commit('SET_RIGHT_PAIRING_PLAYER', data)
  }
}

export default {
  state, getters, mutations, actions
}
