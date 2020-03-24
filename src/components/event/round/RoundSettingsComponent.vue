<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card>
      <!-- Header (toolbar) -->
      <v-app-bar app dark clipped-left color="primary">
        <v-toolbar-title>Раунд {{round.roundNum}}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon fab>
          <v-icon color="white">
            refresh
          </v-icon>
        </v-btn>
        <v-btn icon fab
        @click="dialog = !dialog">
          <v-icon color="white">
            close
          </v-icon>
        </v-btn>
        <v-btn icon fab @click="savePairingGrid">
          <v-icon color="white">
            save
          </v-icon>
        </v-btn>
        <!-- Extension with player swap view -->
        <template v-if="!isEmpty(firstPlayerFowSwap) || !isEmpty(secondPlayerForSwap)" #extension>
          <v-row justify="center">
            <v-toolbar-items>
              <!-- Left player chip -->
              <v-chip :color="clearButtonHovered ? 'additional' : 'white'" v-if="!isEmpty(firstPlayerFowSwap)">
                <v-avatar v-if="firstPlayerFowSwap.player.avatar !== null" left>
                  <v-img :src="firstPlayerFowSwap.player.avatar"></v-img>
                </v-avatar>
                {{firstPlayerFowSwap.player.name}} (Стол {{defineTableNumber(firstPlayerFowSwap.playerIndex)}})
              </v-chip>
              <!-- Swap button -->
              <v-btn text rounded icon
                     v-if="!isEmpty(firstPlayerFowSwap.player) && !isEmpty(secondPlayerForSwap.player)"
                     @click="swapPlayers">
                <v-icon>
                  swap_horiz
                </v-icon>
              </v-btn>
              <!-- Right player chip -->
              <v-chip :color="clearButtonHovered ? 'additional' : 'white'" v-if="!isEmpty(secondPlayerForSwap)">
                <v-avatar v-if="secondPlayerForSwap.player.avatar !== null" left>
                  <v-img :src="secondPlayerForSwap.player.avatar"></v-img>
                </v-avatar>
                {{secondPlayerForSwap.player.name}} (Стол
                {{defineTableNumber(secondPlayerForSwap.playerIndex)}})
              </v-chip>
              <!-- Clear button -->
              <v-btn text icon
                     @click="clearSwapData"
                     @mouseover="clearButtonHovered = true"
                     @mouseleave="clearButtonHovered = false">
                <v-icon>
                  close
                </v-icon>
              </v-btn>
            </v-toolbar-items>
          </v-row>
        </template>
      </v-app-bar>
      <!-- Sidebar (navbar) -->
      <!--<v-navigation-drawer
        class="compact-form"
        app
        v-model="drawer"
        width="200px"
        permanent
        clipped>
        <v-row no-gutters>
          <v-col class="mt-3 ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 1"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              class="styled-input"
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 2"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 3"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 4"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 5"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Стратегия"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Расстановка"
            ></v-select>
          </v-col>
        </v-row>
      </v-navigation-drawer>-->
      <v-content id="body-container"
                 class="pl-200 overflow-y-hidden">
        <v-container fluid >
          <PlayersPairingComponent
            ref="playersPairingComponent"
            @swap-players="clearSwapData"
          ></PlayersPairingComponent>
        </v-container>
      </v-content>
    </v-card>
  </v-dialog>
</template>

<script>
import PlayersPairingComponent from '../pairing/PlayersPairingComponent'
import _ from 'lodash'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
export default {
  name: 'RoundSettingsComponent',
  components: {PlayersPairingComponent},
  props: {
    round: {
      required: true,
      type: Object
    }
  },
  data () {
    return {
      dialog: true,
      clearButtonHovered: false
    }
  },
  computed: {
    firstPlayerFowSwap: {
      get: function () {
        return this.$store.getters.firstPairingPlayer
      },
      set: function (newValue) {
        this.$store.dispatch('INIT_FIRST_PAIRING_PLAYER', {})
      }

    },
    secondPlayerForSwap: {
      get: function () {
        return this.$store.getters.secondPairingPlayer
      },
      set: function (newValue) {
        this.$store.dispatch('INIT_SECOND_PAIRING_PLAYER', {})
      }
    }
  },
  methods: {
    isEmpty: _.isEmpty,
    clearSwapData () {
      this.firstPlayerFowSwap = {}
      this.secondPlayerForSwap = {}
      // Сбрасываем индекс расстановки для верного заполнения игроков при следующем выборе
      this.$refs.playersPairingComponent.nextPlayerIndex = 0
      this.clearButtonHovered = false
    },
    // Вот это мне как-то не очень нравится. Делегирование всех бизнес функций в другой компонент?
    swapPlayers () {
      this.$refs.playersPairingComponent.swapPlayers(this.firstPlayerFowSwap.playerIndex, this.secondPlayerForSwap.playerIndex)
    },
    savePairingGrid () {
      this.$refs.playersPairingComponent.savePairingGrid(this.round.id)
        .then(response => {
          if (response.status === HTTPResponseStatusConstants.OK) {
            this.dialog = false
          }
        })
        .catch(error => console.log(error))
    },
    defineTableNumber (playerIndex) {
      return playerIndex % 2 === 0 ? (playerIndex / 2 + 1) : Math.round(playerIndex / 2)
    }
  }
}
</script>

<style scoped>
  #styled-input {
    height: 40px;
    font-size: 20pt;
  }
  .styled-input label[for] {
    height: 40px;
    font-size: 20pt;
  }
</style>
