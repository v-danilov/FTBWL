<template>
<v-row>
  <v-btn
    absolute
    small
    dark
    fab
    bottom
    right
    color="primary"
    @click.stop="dialog = true"
  >
    <v-icon>{{ editTableIcon }}</v-icon>
  </v-btn>
  <v-dialog v-model="dialog" width="600">
    <v-card>
      <v-card-title class="headline primary white--text" primary-title>
        Обновление очков паринга
      </v-card-title>

      <v-card-text>
        <v-row class="text-center" align="center">
            <v-col>
                <v-divider/>
            </v-col>
            <v-col >
                Стол №{{pairing.tableNumber}}
            </v-col>
            <v-col >
                <v-divider/>
            </v-col>
        </v-row>
        <v-row class="headline primary--text">
             <v-col cols="8">
                Игрок
            </v-col>
            <v-col cols="2" class="text-center">
                TP
            </v-col>
            <v-col cols="2" class="text-center">
                VP
            </v-col>
        </v-row>
        <v-row align="center">
             <v-col cols="8">
               {{ pairing.firstPlayer.user.firstName }}
              <span>{{ pairing.firstPlayer.user.nickname }} </span>
              {{ pairing.firstPlayer.user.lastName }}
            </v-col>
            <v-col cols="2">
                <v-text-field
                    v-model.number="firstPlayerScore.tp"
                    class="centered-input"
                    single-line
                    type="number">
                </v-text-field>
            </v-col>
            <v-col cols="2">
                <v-text-field
                    v-model.number="firstPlayerScore.vp"
                    class="centered-input"
                    single-line
                    type="number">
                </v-text-field>
            </v-col>
        </v-row>
        <v-row align="center">
             <v-col cols="8">
                {{ pairing.secondPlayer.user.firstName }}
              <span>{{ pairing.secondPlayer.user.nickname }} </span>
              {{ pairing.secondPlayer.user.lastName }}
            </v-col>
            <v-col cols="2">
               <v-text-field
                    v-model.number="secondPlayerScore.tp"
                    class="centered-input"
                    single-line
                    type="number">
                </v-text-field>
            </v-col>
            <v-col cols="2">
                <v-text-field
                    v-model.number="secondPlayerScore.vp"
                    class="centered-input"
                    single-line
                    type="number">
                </v-text-field>
            </v-col>
        </v-row>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn rounded="" color="accent" text @click="dialog = false">
          Отмена
        </v-btn>
        <v-btn rounded color="primary" text @click="updatePlayersPoints" :disabled="firstPlayerDataHasNotChanged && secondPlayerDataHasNotChanged">
          Сохранить
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  </v-row>
</template>

<script>
import { mdiCircleEditOutline } from '@mdi/js'
import _ from 'lodash'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  data () {
    return {
      editTableIcon: mdiCircleEditOutline,
      dialog: false,
      firstPlayerScore: Object,
      secondPlayerScore: Object
    }
  },
  props: {
    pairing: {
      type: Object,
      required: true
    }
  },
  beforeMount () {
    // Can loose reactivity here
    this.firstPlayerScore = {tp: this.pairing.firstPlayer.tp, vp: this.pairing.firstPlayer.vp}
    this.secondPlayerScore = {tp: this.pairing.secondPlayer.tp, vp: this.pairing.secondPlayer.vp}
  },
  computed: {
    firstPlayerDataHasNotChanged () {
      return this.firstPlayerScore.tp === this.pairing.firstPlayer.tp && this.firstPlayerScore.vp === this.pairing.firstPlayer.vp
    },
    secondPlayerDataHasNotChanged () {
      return this.secondPlayerScore.tp === this.pairing.secondPlayer.tp && this.secondPlayerScore.vp === this.pairing.secondPlayer.vp
    }
  },
  methods: {
    updatePlayersPoints () {
      let updatedPlayers = []
      if (!this.firstPlayerDataHasNotChanged) {
        let firstPlayer = _.clone(this.pairing.firstPlayer)
        firstPlayer.tp = this.firstPlayerScore.tp
        firstPlayer.vp = this.firstPlayerScore.vp
        firstPlayer.diff = firstPlayer.tp - firstPlayer.vp
        updatedPlayers.push(firstPlayer)
      }

      if (!this.secondPlayerDataHasNotChanged) {
        let secondPlayer = _.clone(this.pairing.secondPlayer)
        secondPlayer.tp = this.secondPlayerScore.tp
        secondPlayer.vp = this.secondPlayerScore.vp
        secondPlayer.diff = secondPlayer.tp - secondPlayer.vp
        updatedPlayers.push(secondPlayer)
      }
      this.$http.put(`/events/${this.$store.getters.currentActiveEventID}/players`, updatedPlayers)
        .then(response => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.SUCCESS, text: 'Players score has been updated.'})
          // If both players has been updated
          if (response.data.length === 2) {
            this.pairing.firstPlayer = response.data[0]
            this.pairing.secondPlayer = response.data[1]
          } else {
            // If only one player has been updated
            if (!this.firstPlayerDataHasNotChanged) {
              this.pairing.firstPlayer = response.data
            }
            if (!this.secondPlayerDataHasNotChanged) {
              this.pairing.secondPlayer = response.data
            }
          }
          this.dialog = false
        })
        .catch(error => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Can not update players score'})
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
.centered-input >>> input {
  text-align: center
}
</style>
