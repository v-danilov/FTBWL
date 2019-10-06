<template>
  <div>
    <template v-if="!loadingError">
    <TournamentInfoHeaderComponent v-if="!componentsHidden"
                                   :selected-tournament="selectedTournament"/>
    <!--<TournamentInfoParametersComponent v-if="componentsHidden"
                                       :selected-tournament="selectedTournament"/>-->
    <TournamentInfoBodyComponent @hide-info-components="changeVisibility"
                                 :rounds="selectedTournament.rounds || []"/>
    </template>
    <template v-else>
      <v-row>
        <v-col cols="12">
          <v-card text>
            <v-card-text>
              <v-avatar size="60%">
                <img src="https://i.pinimg.com/originals/0a/ec/eb/0aecebf4c937d4e947e96c1c5f6d63c7.jpg">
              </v-avatar>
              <h3>ТУРИК 404. ОТПРАВЛЯЮСЬ НА ПОИСКИ!</h3>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </template>
  </div>
</template>

<script>
import TournamentInfoBodyComponent from './side/TournamentInfoBodyComponent'
import TournamentInfoHeaderComponent from './side/TournamentInfoHeaderComponent'
import TournamentInfoParametersComponent from './side/TournamentInfoParametersComponent'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'

export default {
  props: ['tournamentId'],
  name: 'TournamentInfoComponent',
  components: {TournamentInfoParametersComponent, TournamentInfoHeaderComponent, TournamentInfoBodyComponent},
  data () {
    return {
      loadingError: false,
      selectedTournament: Object,
      componentsHidden: true
    }
  },
  methods: {
    changeVisibility (value) {
      console.log(value)
      this.componentsHidden = value
    }
  },
  beforeMount () {
    this.$http.get(`/tournament/${this.tournamentId}`)
      .then(response => {
        this.selectedTournament = response.data
      })
      .catch(error => {
        if (error.response.status === HTTPResponseStatusConstants.NOT_FOUND) {
          this.loadingError = true
          console.log(error)
        }
      })
  }
}
</script>
