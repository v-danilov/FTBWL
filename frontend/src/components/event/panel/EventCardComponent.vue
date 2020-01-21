<template>
  <v-hover>
    <v-card class="mt-3 mx-auto" slot-scope="{ hover }"
            :class="`elevation-${hover ? 12 : 2}`">
      <v-card-title primary-title>
        <span class="mr-2">
          {{event.name}}
        </span>
        <v-spacer></v-spacer>
        <span :style="{color : themeColors.secondary}">{{event.price}} ₽</span>
      </v-card-title>
      <v-card-text @click.stop="openEvent(event.id)">
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
            {{event.place.city.name}}, {{event.place.street}} {{event.place.building}}
            <a href="#" :style="{color: themeColors.secondary}">
              {{event.place.name}}
            </a>
          </v-col>
          <v-col>
            <template v-if="!! event.startDate">
              {{event.timeStart}} -
            </template>
            {{event.timeEnd}}
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
          <v-col>{{event.format.value}}</v-col>
          <v-col>{{event.organizer.lastName}} {{event.organizer.nickName}} {{event.organizer.firstName}}</v-col>
        </v-row>
      </v-card-text>
      <v-card-actions class="pt-0">
        <v-icon  :style="{color : statusInfo.color}" class="mr-1">
          {{statusInfo.icon}}
        </v-icon>
        <span :style="{color : statusInfo.color}">
          {{eventStatusText}}
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
import {EVENT_STATUS_MAP, EVENT_STATUS_CODE} from '../../util/constants/EventStatusNames'
import ConfirmationDialogComponent from '../../dialogs/ConfirmationDialogComponent'

export default {
  name: 'EventCardComponent',
  components: {ConfirmationDialogComponent},
  props: {
    event: Object,
    required: true
  },
  data () {
    return {
      themeColors: this.$vuetify.theme.currentTheme,
      statusMap: EVENT_STATUS_CODE
    }
  },
  computed: {
    eventStatusText () {
      return EVENT_STATUS_MAP.get(this.event.status.code)
    },
    statusInfo () {
      return statusColorize(this.event.status.code)
    }
  },
  methods: {
    openEvent (event) {
      this.$router.push(`/event/${event}`)
    },
    emitOpenDialogEvent () {
      if (UserSession.isAuthenticated()) {
        this.$emit('open-dialog', {id: this.event.id})
      } else {
        this.$router.push('SignIn')
      }
    },
    startEvent () {
      this.$refs.confirmationDialogComponent.pop().then(result => {
        if (result === true) {
          this.$http.get(END_POINTS.EVENT.START + this.event.id).then(response => {
            if (response.status === HTTPResponseStatusConstants.OK) {
              // TODO create endpoint
              this.$router.push('')
            }
          })
        }
      })
    },
    updateEventStatus (newStatus) {
      this.$http.post(END_POINTS.EVENT.UPDATE_STATUS, {eventId: this.event.id, status: newStatus})
        .then(response => {
          if (response.status !== HTTPResponseStatusConstants.OK) {
            console.log('Произошла ошибка обновления статуса турнира.')
          }
        })
    },
    closeEvent () {
      console.log('Турнир закрыт. Пакеда.')
    }
  }
}
</script>
<style scoped>
</style>
