/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  currentActiveEventID: localStorage.getItem('currentActiveEventID') || null
}

const getters = {
  currentActiveEventID: state => state.currentActiveEventID
}

const mutations = {
  SET_CURRENT_ACTIVE_EVENT_ID: (state, payload) => {
    state.currentActiveEventID = payload
  }
}

const actions = {
  INIT_CURRENT_ACTIVE_EVENT_ID: (context, payload) => {
    context.commit('SET_CURRENT_ACTIVE_EVENT_ID', payload)
  }
}

export default {
  state, getters, mutations, actions
}
