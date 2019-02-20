import Vue from 'vue'
import Router from 'vue-router'
import SignUpComponent from '../components/authorization/SignUpComponent'
import SignInComponent from '../components/authorization/SignInComponent'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/signUp',
      name: 'SignUp',
      component: SignUpComponent
    },
    {
      path: '/signIn',
      name: 'SignInComponent',
      component: SignInComponent
    }
  ]
})
