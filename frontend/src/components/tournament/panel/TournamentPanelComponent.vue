 <template>
    <v-layout column>
      <!--Filter -->
      <v-flex>
        <TournamentFilterComponent/>
      </v-flex>
      <v-flex >
        <TournamentCardComponent
        v-for="(tournamentElement, index) in tournaments"
        :key=index
        :tournament=tournamentElement
        @click="openTournament(tournament.id)"
        v-on:open-dialog="openDialog">
        </TournamentCardComponent>
      </v-flex>
      <TournamentRegDialogComponent
        :visible = "showRegForm"
        :tournamentId="focusedTournamentId"
        @close-reg-dialog="showRegForm = false">
      </TournamentRegDialogComponent>
    </v-layout>
</template>

<script>
import TournamentFilterComponent from './filter/TournamentFilterComponent'
import TournamentCardComponent from './TournamentCardComponent'
import {END_POINTS} from '../../util/constants/EndPointsConstants'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
import TournamentRegDialogComponent from './TournamentRegDialogComponent'

export default {
  name: 'TournamentPanelComponent',
  components: {TournamentRegDialogComponent, TournamentCardComponent, TournamentFilterComponent},
  data () {
    return {
      tournaments: [],
      showRegForm: false,
      focusedTournamentId: 0
    }
  },
  methods: {
    openDialog (event) {
      this.focusedTournamentId = event.id
      this.showRegForm = true
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
