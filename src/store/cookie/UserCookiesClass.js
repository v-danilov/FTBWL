import Vue from 'vue'

export default class UserCookiesClass {
  static setToken (token) {
    Vue.$cookies.set('token', token, { expires: '30min' })
  }

  static setAutheticatedUser (user) {
    Vue.$cookies.set('autheticatedUser', user, {expires: '30min'})
  }

  static getToken () {
    return Vue.$cookies.get('token')
  }

  static getAutheticatedUser () {
    return Vue.$cookies.get('autheticatedUser')
  }
}
