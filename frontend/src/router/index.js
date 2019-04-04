import Vue from 'vue'
import Router from 'vue-router'
import SignUpComponent from '../components/authorization/SignUpComponent'
import SignInComponent from '../components/authorization/SignInComponent'
import TournamentPanelComponent from '../components/tournament/TournamentPanelComponent'
import TournamentInfoMainComponent from '../components/tournament/info/TournamentInfoMainComponent'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/tournaments'
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
      path: '/tournamentInfo',
      name: 'TournamentInfoMainComponent',
      component: TournamentInfoMainComponent
    }
  ]
})
