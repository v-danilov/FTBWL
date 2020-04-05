import Vue from 'vue'

export default class UserCookiesClass {
  static setToken (token) {
    Vue.cookie.set('token', token, {expires: '1h'})
  }

  static getToken () {
    return Vue.cookie.get('token')
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
