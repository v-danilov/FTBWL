import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import SignUpComponent from '../components/authorization/SignUpComponent'
import SignInComponent from '../components/authorization/SignInComponent'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
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
