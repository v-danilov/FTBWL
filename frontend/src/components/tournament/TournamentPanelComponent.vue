 <template>
  <v-container fluid >
    <v-layout column>
      <!--Filter -->
      <v-flex>
        <TournamentFilterComponent/>
      </v-flex>
      <v-flex >
        <TournamentCardComponent
        v-for="(tournamentElement, index) in tournaments"
        :key=index
        :tournament=tournamentElement>
        </TournamentCardComponent>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import TournamentFilterComponent from './TournamentFilterComponent'
import TournamentCardComponent from './TournamentCardComponent'
import {END_POINTS} from '../util/constants/EndPointsConstants'
import {HTTPResponseStatusConstants} from '../util/constants/CommonConstants'

export default {
  name: 'TournamentPanelComponent',
  components: {TournamentCardComponent, TournamentFilterComponent},
  data () {
    return {
      tournaments: []
    }
  },
  beforeMount () {
    this.$http.get(END_POINTS.GET_ALL.TOURNAMENTS)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          this.tournaments = response.data.payload
        }
      })
      .catch(reason => {
        console.log(reason)
      })
  }
}
</script>
