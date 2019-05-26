import Vue from 'vue'

export default class UserSession {
  static isAuthenticated () {
    return Vue.cookie.get('user') !== null
  }

  static setUser (userData) {
    Vue.cookie.set('user', userData, {expires: '1h'})
  }

  static getUser () {
    if (this.isAuthenticated()) {
      return this.iterationCopy(Vue.cookie.get('user'))
    }
  }

  static iterationCopy (obj) {
    let copy = {}
    for (let prop in obj) {
      if (obj.hasOwnProperty(prop)) {
        copy[prop] = obj[prop]
      }
    }
    return copy
  }
}
