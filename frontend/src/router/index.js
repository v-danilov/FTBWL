import Vue from 'vue'
import Router from 'vue-router'
import SignUpComponent from '../components/authorization/SignUpComponent'
import SignInComponent from '../components/authorization/SignInComponent'
import TournamentPanelComponent from '../components/tournament/panel/TournamentPanelComponent'
import TournamentInfoMainComponent from '../components/tournament/info/TournamentInfoMainComponent'
import PairingMainComponent from '../components/tournament/pairing/PairingMainComponent'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    // Main redirect to start
    {
      path: '/',
      redirect: '/tournaments'
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
      path: '/tournaments',
      name: 'TournamentPanelComponent',
      component: TournamentPanelComponent
    },
    {
      path: '/tournamentInfo/:tournamentId',
      name: 'TournamentInfoMainComponent',
      component: TournamentInfoMainComponent,
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
