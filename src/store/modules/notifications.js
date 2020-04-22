export const namespaced = true

export const state = {
  notifications: []
}

let notificationID = 1

export const mutations = {
  PUSH (state, notification) {
    state.notifications.push({
      ...notification,
      id: notificationID++
    })
  },
  DELETE (state, notificationToRemove) {
    state.notifications = state.notifications.filter(notif => notif.id !== notificationToRemove.id)
  }
}

export const actions = {
  add ({ commit }, notification) {
    commit('PUSH', notification)
  },
  remove ({ commit }, notificationToRemove) {
    commit('DELETE', notificationToRemove)
  }
}

export default {
  namespaced, state, mutations, actions
}
