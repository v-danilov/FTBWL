<template>
  <v-hover>
    <v-card class="mt-3 mx-auto" slot-scope="{ hover }"
            :class="`elevation-${hover ? 12 : 2}`">
      <v-card-title primary-title>
        <span class="mr-2">
          {{tournament.name}}
        </span>
        <v-spacer></v-spacer>
        <span :style="{color : themeColors.secondary}">{{tournament.price}} ₽</span>
      </v-card-title>
      <v-card-text @click.stop="openTournament(tournament.id)">
        <v-row no-gutters>
          <v-col>
            <span>Где</span>
          </v-col>
          <v-col>
            <span>Когда</span>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <a href="#" :style="{color: themeColors.secondary}">
              {{tournament.place}}
            </a>
          </v-col>
          <v-col>
            <template v-if="!! tournament.startDate">
              {{tournament.startDate}} -
            </template>
            {{tournament.endDate}}
          </v-col>
        </v-row>
        <v-row class="ma-2">
          <v-divider></v-divider>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <span>Формат</span>
          </v-col>
          <v-col>
            <span>Организатор</span>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>{{tournament.format}}</v-col>
          <v-col>{{tournament.organizer}}</v-col>
        </v-row>
      </v-card-text>
      <v-card-actions class="pt-0">
        <v-icon  :style="{color : statusInfo.color}" class="mr-1">
          {{statusInfo.icon}}
        </v-icon>
        <span :style="{color : statusInfo.color}">
          {{tournamentStatusText}}
        </span>
         <v-spacer></v-spacer>
        <v-btn icon @click="emitOpenDialogEvent" class="pb-7">
          <v-icon color="primary" large>assignment_turned_in</v-icon>
        </v-btn>
       </v-card-actions>
    </v-card>
  </v-hover>
</template>
<script>
import statusColorize from '../../util/statusIconWithColor'
import UserSession from '../../../store/cookie/userSessionClass'
import {END_POINTS} from '../../util/constants/EndPointsConstants'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
import {TOURNAMENT_STATUS_NAMES, TOURNAMENT_SYSTEM_NAMES} from '../../util/constants/TournamentStatusNames'
import ConfirmationDialogComponent from '../../dialogs/ConfirmationDialogComponent'

export default {
  name: 'TournamentCardComponent',
  components: {ConfirmationDialogComponent},
  props: {
    tournament: Object,
    required: true
  },
  data () {
    return {
      themeColors: this.$vuetify.theme.currentTheme,
      statusMap: TOURNAMENT_SYSTEM_NAMES
    }
  },
  computed: {
    tournamentStatusText () {
      return TOURNAMENT_STATUS_NAMES.get(this.tournament.status.systemName)
    },
    statusInfo () {
      return statusColorize(this.tournament.status.systemName)
    }
  },
  methods: {
    openTournament (tournamentId) {
      this.$router.push(`/tournamentInfo/${tournamentId}`)
    },
    emitOpenDialogEvent () {
      if (UserSession.isAuthenticated()) {
        this.$emit('open-dialog', {id: this.tournament.id})
      } else {
        this.$router.push('SignIn')
      }
    },
    startTournament () {
      this.$refs.confirmationDialogComponent.pop().then(result => {
        if (result === true) {
          this.$http.get(END_POINTS.TOURNAMENT.START + this.tournament.id).then(response => {
            if (response.status === HTTPResponseStatusConstants.OK) {
              // TODO create endpoint
              this.$router.push('')
            }
          })
        }
      })
    },
    updateTournamentStatus (newStatus) {
      this.$http.post(END_POINTS.TOURNAMENT.UPDATE_STATUS, {tournamentId: this.tournament.id, status: newStatus})
        .then(response => {
          if (response.status !== HTTPResponseStatusConstants.OK) {
            console.log('Произошла ошибка обновления статуса турнира.')
          }
        })
    },
    closeTournament () {
      console.log('Турнир закрыт. Пакеда.')
    }
  }
}
</script>
<style scoped>
</style>
