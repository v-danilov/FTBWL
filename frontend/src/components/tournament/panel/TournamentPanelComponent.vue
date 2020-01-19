<template>
  <v-row align="stretch">
    <v-col>
      <v-row>
        <!--Filter -->
        <v-col :cols="filterWidth">
          <div transition="scroll-y-transition">
            <v-btn v-if="filter.isHidden"
                   rounded color="primary"
                   @click="filter.isHidden = !filter.isHidden">
              <v-icon large>search</v-icon>
              Открыть фильтр
            </v-btn>
            <TournamentFilterComponent v-else @close-tournament-filter="filter.isHidden = true"/>
          </div>
        </v-col>
        <v-col cols="3">
          <v-btn color="primary"
                 rounded
                 @click="showCreateTournamentDialog = !showCreateTournamentDialog">
            <v-icon large>add_circle_outline</v-icon>
            Создать турнир
          </v-btn>
          <TournamentCreateComponent v-if="showCreateTournamentDialog"
                                     :visible="showCreateTournamentDialog"
                                     @close-tournament-create-dialog="showCreateTournamentDialog = false">
          </TournamentCreateComponent>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="4" v-for="(tournamentElement, index) in tournaments" :key=index>
          <TournamentCardComponent
            :tournament=tournamentElement
            @click="openTournament(tournament.id)"
            v-on:open-dialog="openDialog">
          </TournamentCardComponent>
        </v-col>
        <TournamentRegDialogComponent
          :visible="showRegForm"
          :tournamentId="focusedTournamentId"
          @close-reg-dialog="showRegForm = false">
        </TournamentRegDialogComponent>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
  import TournamentFilterComponent from './filter/TournamentFilterComponent'
  import TournamentCardComponent from './TournamentCardComponent'
  import {END_POINTS} from '../../util/constants/EndPointsConstants'
  import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
  import TournamentRegDialogComponent from './TournamentRegDialogComponent'
  import TournamentCreateComponent from './TournamentCreateComponent'

  export default {
    name: 'TournamentPanelComponent',
    components: {
      TournamentCreateComponent,
      TournamentRegDialogComponent,
      TournamentCardComponent,
      TournamentFilterComponent
    },
    data() {
      return {
        cardWidth: 3,
      filter: {
        isHidden: true
      },
      tournaments: [],
      showRegForm: false,
      showCreateTournamentDialog: false,
        focusedTournamentId: ''
    }
  },
  methods: {
    openDialog (event) {
      this.focusedTournamentId = event.id
      this.showRegForm = true
    }
  },
  computed: {
    filterWidth () {
      return this.filter.isHidden ? 3 : 12
    }
  },
  created () {
    this.$http.get(END_POINTS.GET_ALL.TOURNAMENTS)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          this.tournaments = response.data
        }
      })
      .catch(reason => {
        console.log(reason)
      })
  }
}
</script>
