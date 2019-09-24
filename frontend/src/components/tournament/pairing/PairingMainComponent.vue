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
            <v-list-item-avatar>
              <v-img :src="element.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-card>
                <v-card-text>
                  {{element.name}}
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="2">
        <v-list disabled>
          <v-list-item
            v-for="i in dataFromServer.tablesNumber"
            :key="i"
          >
            <v-list-item-content>
              <v-card>
                <v-card-text>
                  <span>Стол №{{i}}</span>
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="4">
        <v-list>
          <v-list-item color="primary"
                       v-for="(element, index) in playersForPairing"
                       v-if="index %2 !== 0"
                       :key="element.id"
                       @click="selectPlayerForSwap(element, index)"
          >
            <v-list-item-avatar>
              <v-img :src="element.avatar"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-card>
                <v-card-text>
                  {{element.name}}
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
import draggable from 'vuedraggable'

export default {
  name: 'PairingMainComponent',
  components: {
    draggable
  },
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
    // TODO get tournament data for pairing creation
    this.$http.get('https://randomuser.me/api/?results=40').then(response => {
      // response.data.results.forEach(splitToLeftAndRight)
      response.data.results.forEach(e => this.playersForPairing.push({
        name: e.name.first.replace(/^\w/, c => c.toUpperCase()) + ' ' + e.name.last.replace(/^\w/, c => c.toUpperCase()),
        avatar: e.picture.medium
      }))
      this.dataFromServer.tablesNumber = 20
    })
  },
  methods: {
    swapPlayers (fIndex, sIndex) {
      const playerForSwap = this.playersForPairing[fIndex]
      this.playersForPairing[fIndex] = this.playersForPairing[sIndex]
      this.playersForPairing[sIndex] = playerForSwap
      this.$emit('swap-players')
    },
    selectPlayerForSwap (player, playerIndex) {
      let choseDispatchActionBasedOnOrder = function (vm) {
        if (vm.nextPlayerIndex === 0) {
          vm.nextPlayerIndex = -1
          return 'INIT_FIRST_PAIRING_PLAYER'
        } else {
          vm.nextPlayerIndex = 0
          return 'INIT_SECOND_PAIRING_PLAYER'
        }
      }
      const vm = this
      this.$store.dispatch(choseDispatchActionBasedOnOrder(vm), {player: player, playerIndex: playerIndex})
    }
  }
}
</script>

<style scoped>
</style>
