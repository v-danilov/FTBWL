import Vue from 'vue'
import Router from 'vue-router'
import RegistrationComponent from '../components/authorization/RegistrationComponent'
import LoginComponent from '../components/authorization/LoginComponent'
import EventPanelComponent from '../components/event/panel/EventPanelComponent'
import EventInfoMainComponent from '../components/event/info/EventInfoMainComponent'
import PlayersPairingComponent from '../components/event/pairing/PlayersPairingComponent'

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
      path: '/registration',
      name: 'RegistrationComponent',
      component: RegistrationComponent
    },
    {
      path: '/login',
      name: 'LoginComponent',
      component: LoginComponent
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
      component: PlayersPairingComponent
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
