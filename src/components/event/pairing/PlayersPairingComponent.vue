<template>
  <div>
    <v-row justify="center">
      <v-col cols="4">
        <v-list>
          <v-list-item
            v-for="(element, index) in playersForPairing"
            v-if="index %2 === 0"
            :key="element.id"
            @click="selectPlayerForSwap(element, index)"
          >
            <v-list-item-avatar v-if="element.avatar !== null">
              <v-img :src="element.avatar" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-card>
                <v-card-text class="text-center">
                  {{ element.name }}
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="2">
        <v-list disabled>
          <v-list-item
            v-for="i in playersForPairing.length / 2"
            :key="i"
          >
            <v-list-item-content>
              <v-card>
                <v-card-text class="text-center">
                  <span>Стол №{{ i }}</span>
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="4">
        <v-list>
          <v-list-item
            v-for="(element, index) in playersForPairing"
            v-if="index %2 !== 0"
            :key="element.id"
            @click="selectPlayerForSwap(element, index)"
            color="primary"
          >
            <v-list-item-avatar v-if="element.avatar !== null">
              <v-img :src="element.avatar" />
            </v-list-item-avatar>

            <v-list-item-content>
              <v-card>
                <v-card-text class="text-center">
                  {{ element.name }}
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
  </div>
</template>

<script>

import { END_POINTS } from '../../util/constants/EndPointsConstants'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  name: 'PlayersPairingComponentVue',
  data () {
    return {
      drag: false,
      playersForPairing: [],
      dataFromServer: {
        tablesNumber: 0,
        players: []
      },
      nextPlayerIndex: 0 // Нужен для поочерёдного распределния между левой и правой стороной при последовательном выборе игроков
    }
  },
  created () {
    this.$http.get(END_POINTS.EVENTS.DEFAULT + this.$store.getters.currentActiveEventID)
      .then(response => {
        response.data.players.forEach(e => {
          // console.log(e)
          const playerData = {
            id: e.id,
            name: e.user.fullName,
            nickname: e.user.nickname,
            avatar: null // TODO implement user avatar?
          }
          this.playersForPairing.push(playerData)
        })
      })
      .catch(err => {
        this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Can not load players data'})
        console.log(err)
      })
  },
  methods: {
    swapPlayers (fIndex, sIndex) {
      const firstPlayer = this.playersForPairing[fIndex]
      const secondPlayer = this.playersForPairing[sIndex]
      // Using splice to swap array values for Vue automatic reactivity
      this.playersForPairing.splice(fIndex, 1, secondPlayer)
      this.playersForPairing.splice(sIndex, 1, firstPlayer)
      this.$emit('swap-players')
    },
    selectPlayerForSwap (player, playerIndex) {
      const choseDispatchActionBasedOnOrder = function (vm) {
        if (vm.nextPlayerIndex === 0) {
          vm.nextPlayerIndex = -1
          return 'INIT_FIRST_PAIRING_PLAYER'
        } else {
          vm.nextPlayerIndex = 0
          return 'INIT_SECOND_PAIRING_PLAYER'
        }
      }
      const vm = this
      this.$store.dispatch(choseDispatchActionBasedOnOrder(vm), { player: player, playerIndex: playerIndex })
    },
    savePairingGrid (roundID) {
      const pairingDTO = []
      let tableCounter = 1
      for (let i = 0; i < this.playersForPairing.length; i = i + 2) {
        pairingDTO.push({
          firstPlayer: { id: this.playersForPairing[i].id },
          secondPlayer: { id: this.playersForPairing[i + 1].id },
          tableNumber: tableCounter++
        })
      }
      console.log(pairingDTO)
      return this.$http.post(END_POINTS.PAIRINGS_BY_ROUND.DEFAULT + roundID, pairingDTO)
    }
  }
}
</script>

<style scoped>
</style>
