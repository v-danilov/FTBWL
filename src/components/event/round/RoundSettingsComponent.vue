<template>
  <v-dialog
    v-model="value"
    fullscreen
    hide-overlay
    transition="dialog-bottom-transition"
  >
    <v-card>
      <!-- Header (toolbar) -->
      <v-app-bar
        app
        dark
        clipped-left
        color="primary"
      >
        <v-toolbar-title>Раунд {{ round.roundNum }}</v-toolbar-title>
        <v-spacer />
        <v-btn
          icon
          fab
        >
          <v-icon color="white">
            refresh
          </v-icon>
        </v-btn>
        <v-btn
          @click="closeDialog"
          icon
          fab
        >
          <v-icon color="white">
            close
          </v-icon>
        </v-btn>
        <v-btn
          @click="savePairingGrid"
          icon
          fab
        >
          <v-icon color="white">
            save
          </v-icon>
        </v-btn>
        <!-- Extension with player swap view -->
        <template
          v-if="!isEmpty(leftPlayerForSwap) || !isEmpty(rightPlayerForSwap)"
          #extension
        >
          <v-row justify="center">
            <v-toolbar-items>
              <!-- Left player chip -->
              <v-chip color="white" v-if="!isEmpty(leftPlayerForSwap)">
                <v-avatar
                  v-if="leftPlayerForSwap.avatar !== null"
                  left
                >
                  <v-img :src="leftPlayerForSwap.avatar" />
                </v-avatar>
                {{ leftPlayerForSwap.name }} (Стол {{ leftPlayerForSwap.tableNumber }})
              </v-chip>
              <!-- Swap button -->
              <v-btn
                v-if="!isEmpty(leftPlayerForSwap) && !isEmpty(rightPlayerForSwap)"
                @click="swapPlayers"
                text
                rounded
                icon
              >
                <v-icon>
                  swap_horiz
                </v-icon>
              </v-btn>
              <!-- Right player chip -->
              <v-chip color="white" v-if="!isEmpty(rightPlayerForSwap)">
                <v-avatar
                  v-if="rightPlayerForSwap.avatar !== null"
                  left
                >
                  <v-img :src="rightPlayerForSwap.avatar" />
                </v-avatar>
                {{ rightPlayerForSwap.name }} (Стол
                {{ rightPlayerForSwap.tableNumber }})
              </v-chip>
              <!-- Clear button -->
              <v-btn
                @click="clearSwapData"
                @mouseover="clearButtonHovered = true"
                @mouseleave="clearButtonHovered = false"
                text
                icon
              >
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
      <v-content
        id="body-container"
        class="pl-200 overflow-y-hidden"
      >
        <v-container fluid>
          <PlayersPairingComponent
            :round-id="round.id"
            :pairings="round.pairings"
            @swap-players="clearSwapData"
            ref="playersPairingComponent"
          />
        </v-container>
      </v-content>
    </v-card>
  </v-dialog>
</template>

<script>
import PlayersPairingComponent from '../pairing/PlayersPairingComponent'
import _ from 'lodash'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  name: 'RoundSettingsComponent',
  components: { PlayersPairingComponent },
  props: {
    value: Boolean,
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
    leftPlayerForSwap: {
      get: function () {
        return this.$store.getters.pairingPlayerLeft
      },
      set: function (newValue) {
        this.$store.dispatch('setPairingPlayerLeft', {})
      }

    },
    rightPlayerForSwap: {
      get: function () {
        return this.$store.getters.pairingPlayerRight
      },
      set: function (newValue) {
        this.$store.dispatch('setPairingPlayerRight', {})
      }
    }
  },
  methods: {
    isEmpty: _.isEmpty,
    closeDialog () {
      this.clearSwapData()
      this.$emit('input', false)
    },
    clearSwapData () {
      this.leftPlayerForSwap = {}
      this.rightPlayerForSwap = {}
      // Сбрасываем индекс расстановки для верного заполнения игроков при следующем выборе
      this.$refs.playersPairingComponent.nextPlayerIndex = 0
      this.clearButtonHovered = false
    },
    // Вот это мне как-то не очень нравится. Делегирование всех бизнес функций в другой компонент?
    swapPlayers () {
      this.$refs.playersPairingComponent.swapPlayers()
    },
    savePairingGrid () {
      this.$refs.playersPairingComponent.savePairingGrid(this.round.id)
        .then(response => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.SUCCESS, text: 'Pairings saved.'})
        })
        .catch(error => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Error while saving pairings.'})
          console.log(error)
        })
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
