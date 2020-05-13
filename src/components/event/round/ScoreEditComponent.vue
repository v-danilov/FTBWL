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
                    v-model="firstPlayerScore.tp"
                    class="centered-input"
                    single-line
                    type="number">
                </v-text-field>
            </v-col>
            <v-col cols="2">
                <v-text-field
                    v-model="firstPlayerScore.vp"
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
                    v-model="secondPlayerScore.tp"
                    class="centered-input"
                    single-line
                    type="number">
                </v-text-field>
            </v-col>
            <v-col cols="2">
                <v-text-field
                    v-model="secondPlayerScore.vp"
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

export default {
  data () {
    return {
      editTableIcon: mdiCircleEditOutline,
      dialog: false
    }
  },
  props: {
    pairing: {
      type: Object,
      required: true
    }
  },
  created () {
    console.log('rected')
    console.log(this.firstPlayerScore)
    console.log(this.secondPlayerScore)
  },
  computed: {
    firstPlayerScore () {
      return {tp: this.pairing.firstPlayer.tp, vp: this.pairing.firstPlayer.vp}
    },
    secondPlayerScore () {
      return {tp: this.pairing.secondPlayer.tp, vp: this.pairing.secondPlayer.vp}
    },
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
      console.log(updatedPlayers)
    }
  }
}
</script>

<style scoped>
.centered-input >>> input {
  text-align: center
}
</style>
