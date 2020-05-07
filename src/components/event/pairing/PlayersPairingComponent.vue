<template>
  <div>
    <v-row justify="center">
      <v-col cols="4">
        <v-list>
          <v-list-item
            v-for="(pairing, index) in pairings"
            :key="pairing.firstPlayer.id"
            @click="selectPlayerForSwap(index, -1)"
          >
            <v-list-item-avatar v-if="pairing.firstPlayer.avatar !== undefined">
              <v-img :src="pairing.firstPlayer.avatar" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-card>
               <v-card-text class="text-center" v-html="playerFullName(pairing.firstPlayer)"/>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="2">
        <v-list disabled>
          <v-list-item
             v-for="(pairing, index) in pairings"
            :key="index"
          >
            <v-list-item-content>
              <v-card>
                <v-card-text class="text-center">
                  <span>Стол №{{ pairing.tableNumber }}</span>
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="4">
        <v-list>
          <v-list-item
            v-for="(pairing, index) in pairings"
            :key="pairing.secondPlayer.id"
            @click="selectPlayerForSwap(index, 1)"
            color="primary"
          >
            <v-list-item-avatar v-if="pairing.secondPlayer.avatar !== undefined">
              <v-img :src="pairing.secondPlayer.avatar" />
            </v-list-item-avatar>

            <v-list-item-content>
              <v-card>
                <v-card-text class="text-center" v-html="playerFullName(pairing.secondPlayer)"/>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
  </div>
</template>

<script>

import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  name: 'PlayersPairingComponentVue',
  props: {
    pairings: {
      type: Array,
      default: function () {
        return []
      }
    },
    roundId: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      pairingsPost: false,
      players: [],
      nextPlayerIndex: 0 // Нужен для поочерёдного распределния между левой и правой стороной при последовательном выборе игроков,
    }
  },
  created () {
    if (this.pairings.length === 0) {
      this.loadPlayersInfo()
        .then(response => {
          this.preparePairings()
          this.pairingsPost = true
        })
        .catch(error => console.log(error))
    }
  },
  methods: {
    swapPlayers () {
      const leftPlayerForSwap = this.$store.getters.pairingPlayerLeft
      const rightPlayerForSwap = this.$store.getters.pairingPlayerRight

      if (leftPlayerForSwap.playerPosition === -1) {
        // Swap first players
        if (rightPlayerForSwap.playerPosition === -1) {
          const tmp = this.pairings[leftPlayerForSwap.pairingIndex].firstPlayer
          this.pairings[leftPlayerForSwap.pairingIndex].firstPlayer = this.pairings[rightPlayerForSwap.pairingIndex].firstPlayer
          this.pairings[rightPlayerForSwap.pairingIndex].firstPlayer = tmp
        } else {
          // Swap the first player with the second one
          const tmp = this.pairings[leftPlayerForSwap.pairingIndex].firstPlayer
          this.pairings[leftPlayerForSwap.pairingIndex].firstPlayer = this.pairings[rightPlayerForSwap.pairingIndex].secondPlayer
          this.pairings[rightPlayerForSwap.pairingIndex].secondPlayer = tmp
        }
      } else {
        // Swap the second player with the first one
        if (rightPlayerForSwap.playerPosition === -1) {
          const tmp = this.pairings[leftPlayerForSwap.pairingIndex].secondPlayer
          this.pairings[leftPlayerForSwap.pairingIndex].secondPlayer = this.pairings[rightPlayerForSwap.pairingIndex].firstPlayer
          this.pairings[rightPlayerForSwap.pairingIndex].firstPlayer = tmp
        } else {
          // Swap second players
          const tmp = this.pairings[leftPlayerForSwap.pairingIndex].secondPlayer
          this.pairings[leftPlayerForSwap.pairingIndex].secondPlayer = this.pairings[rightPlayerForSwap.pairingIndex].secondPlayer
          this.pairings[rightPlayerForSwap.pairingIndex].secondPlayer = tmp
        }
      }

      this.$emit('swap-players')
    },
    // playerPosition необходим что понимать какоторо игрока из паринга савапаем (-1 - первый/левый, 1 - второй/правый)
    selectPlayerForSwap (pairingIndex, playerPosition) {
      const selectedPairing = this.pairings[pairingIndex]
      const selectedPlayer = playerPosition === -1 ? selectedPairing.firstPlayer : selectedPairing.secondPlayer
      const player = {
        avatar: selectedPlayer.avatar || null,
        name: selectedPlayer.user.firstName + ' ' + selectedPlayer.user.lastName,
        tableNumber: selectedPairing.tableNumber,
        pairingIndex: pairingIndex,
        playerPosition: playerPosition
      }

      // Fill sides of app bar with players chips (left if frist click, right  if second)
      const choseDispatchActionBasedOnOrder = function (vm) {
        if (vm.nextPlayerIndex === 0) {
          vm.nextPlayerIndex = -1
          return 'setPairingPlayerLeft'
        } else {
          vm.nextPlayerIndex = 0
          return 'setPairingPlayerRight'
        }
      }

      const vm = this
      this.$store.dispatch(choseDispatchActionBasedOnOrder(vm), player)
    },
    savePairingGrid (roundID) {
      return this.pairingsPost ? this.$http.post(`/rounds/${roundID}/pairings`, this.pairings) : this.$http.put(`/rounds/${roundID}/pairings`, this.pairings)
    },
    loadPlayersInfo () {
      const eventID = this.$store.getters.currentActiveEventID
      const filterRequest = 'isConfirmed:true'
      return this.$http.get(`/events/${eventID}/players?filter=${filterRequest}`)
        .then(response => {
          this.players = response.data
        })
        .catch(err => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Can not load players data'})
          console.log(err)
        })
    },
    preparePairings () {
      let tableNumber = 1
      for (let index = 0; index < this.players.length; index = index + 2) {
        const pairing = {
          firstPlayer: this.players[index],
          secondPlayer: this.players[index + 1],
          tableNumber: tableNumber++
        }
        this.pairings.push(pairing)
      }
    },
    playerFullName (player) {
      return player.user.firstName + ' <span>' + player.user.nickname + '</span> ' + player.user.lastName
    }
  }
}
</script>

<style scoped>
</style>
