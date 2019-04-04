<template>
  <div>
    <TournamentInfoHeaderComponent :selected-tournament="selectedTournament"/>
    <TournamentInfoParametersComponent :selected-tournament="selectedTournament"/>
    <TournamentInfoBodyComponent :rounds="selectedTournament.rounds || []"/>
  </div>
</template>

<script>
import TournamentInfoBodyComponent from './side/TournamentInfoBodyComponent'
import TournamentInfoHeaderComponent from './side/TournamentInfoHeaderComponent'
import TournamentInfoParametersComponent from './side/TournamentInfoParametersComponent'

export default {
  name: 'TournamentInfoComponent',
  components: { TournamentInfoParametersComponent, TournamentInfoHeaderComponent, TournamentInfoBodyComponent },
  data () {
    return {
      selectedTournament: Object
    }
  },
  methods: {},
  beforeCreate () {
    this.$http.get('/tournament/1')
      .then(response => {
        this.selectedTournament = response.data
      })
      .catch(reason => {
        console.log(reason)
      })
  }
}
</script>
