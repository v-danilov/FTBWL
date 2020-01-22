import Vue from 'vue'

export default class UserSession {
  static isAuthenticated () {
    // return Vue.cookie.get('user') !== null
    return true
  }

  static setUser (userData) {
    Vue.cookie.set('user', userData, {expires: '1h'})
  }

  static getUser () {
    if (this.isAuthenticated()) {
      return this.iterationCopy(Vue.cookie.get('user'))
    }
    // fixme if not?
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
