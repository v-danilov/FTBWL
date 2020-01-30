import Vue from 'vue'
import Router from 'vue-router'
import SignUpComponent from '../components/authorization/SignUpComponent'
import SignInComponent from '../components/authorization/SignInComponent'
import EventPanelComponent from '../components/event/panel/EventPanelComponent'
import EventInfoMainComponent from '../components/event/info/EventInfoMainComponent'
import PairingMainComponent from '../components/event/pairing/PairingMainComponent'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    // Main redirect to start
    {
      path: '/',
      redirect: '/events'
      // meta: { requiresAuth: true }
    },
    // Redirect for 404
    {
      path: '*',
      component: {
        template: '<h3> НУ НЕ ШМОГЛА(</h3>'
      }
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
    },
    {
      path: '/events',
      name: 'EventPanelComponent',
      component: EventPanelComponent
    },
    {
      path: '/events/:eventId',
      name: 'EventInfoMainComponent',
      component: EventInfoMainComponent,
      props: true
    },
    {
      path: '/statistic',
      name: 'Name',
      component: PairingMainComponent
    }
  ]
})
/* router.beforeEach((to, from, next) => {
  if (to.meta.requiredAuth && notAuthintecated) {
    router.push('SignIn')
  } else {
    next()
  }
}) */

export default router
