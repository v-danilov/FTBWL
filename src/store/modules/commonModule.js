/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  currentActiveEventID: localStorage.getItem('currentActiveEventID') || null,
  routeToJump: localStorage.getItem('routeToJump') || '/'
}

const getters = {
  currentActiveEventID: state => state.currentActiveEventID,
  routeToJump: state => state.routeToJump
}

const mutations = {
  SET_CURRENT_ACTIVE_EVENT_ID: (state, payload) => {
    state.currentActiveEventID = payload
  },
  SET_ROUTE_TO_JUMP: (state, payload) => {
    state.routeToJump = payload
  }
}

const actions = {
  initCurrentEventID: (context, payload) => {
    context.commit('SET_CURRENT_ACTIVE_EVENT_ID', payload)
  },
  saveRouteToJump: (context, payload) => {
    context.commit('SET_ROUTE_TO_JUMP', payload)
  }
}

export default {
  state, getters, mutations, actions
}
