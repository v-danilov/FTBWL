<template>
  <v-hover>
    <v-card class="mt-3 mx-auto" slot-scope="{ hover }"
            :class="`elevation-${hover ? 12 : 2}`">
      <v-card-title primary-title>
        <h2 class="mr-2">{{tournament.name}}</h2>
        <v-icon  :style="{color : statusInfo.color}" class="mr-1">{{statusInfo.icon}}</v-icon>
        <span :style="{color : statusInfo.color}">{{tournament.status}}</span>
        <v-spacer></v-spacer>
        <h2 :style="{color : themeColors.secondary}">{{tournament.price}} ₽</h2>
      </v-card-title>
      <v-card-title @click.stop="openTournament(tournament.id)" class="pb-0 pt-0">
        <v-layout row wrap>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span>Где: </span>
            <a href="#" :style="{color : themeColors.secondary}">{{tournament.place}}</a>
          </v-flex>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span>Организатор:</span>
            <a href="#" :style="{color : themeColors.secondary}">{{tournament.organizer}}</a>
          </v-flex>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span>Когда: </span>
            <template v-if="!! tournament.startDate">
              {{tournament.startDate}} -
            </template>
            {{tournament.endDate}}
          </v-flex>
          <v-flex xs5 sm5 md5 class="text-xs-left text-sm-left text-md-left">
            <span>Формат: </span>
            {{tournament.format}}
          </v-flex>
        </v-layout>
      </v-card-title>
      <v-card-actions class="pt-0">
         <v-spacer></v-spacer>
        <v-btn
          color="primary"
          round
          @click="openConfirmDialog"
        >
          Начать турнир
        </v-btn>
        <ConfirmationDialogComponent header-text="Подтвердите начало проведения турнира"
                                     v-bind:body-text=confirmationText
                                     v-bind:is-multi-action="true"
                                     ref="confirmationDialogComponent">
        </ConfirmationDialogComponent>
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

export default {
  name: 'TournamentCardComponent',
  props: {
    tournament: Object,
    required: true
  },
  data () {
    return {
      themeColors: this.$vuetify.theme
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
    openConfirmDialog () {
      console.log(this.$refs)
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
    }
  },
  computed: {
    statusInfo () {
      return statusColorize(this.tournament.status)
    },
    confirmationText () {
      return '<div>' +
        '<div>' +
        '<span>Название турнира: </span>' +
        this.tournament.name +
        '</div>' +
        '<div>' +
        '<span>Дата начала проведения: </span>' +
        '27 июня 2019 19:45' +
        '</div>' +
        '</div>'
    }
  }
}
</script>
<style scoped>
</style>
