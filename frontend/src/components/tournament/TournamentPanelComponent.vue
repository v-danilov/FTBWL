 <template>
    <v-layout column>
      <!--Filter -->
      <v-flex text-xs-left>
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
          this.tournaments = response.data
        }
      })
      .catch(reason => {
        console.log(reason)
      })
  }
}
</script>
