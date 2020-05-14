import Vue from 'vue'

export default class UserCookiesClass {
  static setToken (token) {
    Vue.cookie.set('token', token, { expires: '30min' })
  }

  static getToken () {
    return Vue.cookie.get('token')
  }

  static iterationCopy (obj) {
    const copy = {}
    for (const prop in obj) {
      if (obj.hasOwnProperty(prop)) {
        copy[prop] = obj[prop]
      }
    }
    return copy
  }
}
