<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-tabs
    slider-color="additional"
    background-color="primary"
    fixed-tabs
    class="mt-3"
  >
    <!-- Вкладка участников -->
    <v-tab ripple>
      Участники
    </v-tab>
    <v-tab-item>
      <v-card>
        <!-- Header -->
        <v-card-title>
          <v-row dense>
            <v-col cols="1">
              <span>№</span>
            </v-col>
            <v-col cols="3">
              <span>Игрок</span>
            </v-col>
            <v-col cols="2">
              <span>Фракция</span>
            </v-col>
            <v-col
              class="text-center"
              cols="1"
            >
              <span>TP</span>
            </v-col>
            <v-col
              class="text-center"
              cols="1"
            >
              <span>VP</span>
            </v-col>
            <v-col
              class="text-center"
              cols="1"
            >
              <span>Diff</span>
            </v-col>
            <v-col
              class="text-center"
              cols="3"
            >
              <span>Участие</span>
            </v-col>
          </v-row>
        </v-card-title>
        <!-- Body -->
        <v-card-text>
          <v-hover
            v-slot:default="{ hover }"
            v-for="(player, index) in players"
            :key="index"
          >
            <v-row
              :class="{ 'on-hover': hover }"
              class="selected-row"
              dense
              justify="center"
            >
              <v-col cols="1">
                {{ index + 1 }}
              </v-col>
              <v-col cols="3">
                {{ player.user.fullName }}
                <em>aka <span>{{ player.user.nickname }}</span></em>
              </v-col>
              <v-col cols="2">
                {{ player.faction.systemName }}
              </v-col>
              <v-col
                cols="1"
                class="text-center"
              >
                {{ player.tp }}
              </v-col>
              <v-col
                class="text-center"
                cols="1"
              >
                {{ player.vp }}
              </v-col>
              <v-col
                cols="1"
                class="text-center"
              >
                {{ player.diff }}
              </v-col>
              <v-col
                class="text-center"
                cols="3"
              >
                <v-btn
                  small
                  rounded
                  :outlined="!player.confirmed"
                  :color="player.confirmed ? 'secondary' : 'accent'"
                  @click="confirmPlayerParticipation(player)"
                >
                  {{player.confirmed ? 'Подтверждён' : 'Подтвердить'}}
                </v-btn>
              </v-col>
            </v-row>
          </v-hover>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn
            :loading="apiCallInProcess"
            :disabled="apiCallInProcess"
            @click="confirmPlayers"
            text
            outlined
            rounded
            color="primary"
          >
            Сохранить
            <template v-slot:loader>
              <span class="custom-loader">
                <v-icon light>cached</v-icon>
              </span>
            </template>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-tab-item>
    <!-- Вкладки раундов -->
    <template v-for="(round, index) in selectedEvent.rounds">
      <v-tab
        :key="index"
        @change="selectedRoundNumber = index"
        ripple
      >
        Раунд {{ index + 1 }}
      </v-tab>
      <v-tab-item :key="index">
       <Round :round="round"></Round>
        <v-row>
          <!-- Left column with tables -->
          <v-col
            v-for="(pairing, index) in selectedEvent.rounds[selectedRoundNumber].pairings"
            :key="index"
            cols="4"
          >
            <TablesInfoComponent :pairing="pairing" />
          </v-col>
        </v-row>
      </v-tab-item>
    </template>
    <v-spacer />
    <v-btn
      @click="hideEventInfo = !hideEventInfo"
      icon
    >
      <v-icon v-if="!hideInfoComponents">
        keyboard_arrow_down
      </v-icon>
      <v-icon v-else>
        keyboard_arrow_up
      </v-icon>
    </v-btn>
  </v-tabs>
</template>

<script>
import TablesInfoComponent from '@/components/event/round/TablesInfoComponent'
import RoundComponent from '@/components/event/round/RoundComponent'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  name: 'EventInfoBodyComponent',
  components: { Round: RoundComponent, TablesInfoComponent },
  props: {
    selectedEvent: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      vuetifyTheme: this.$vuetify.theme,
      hideEventInfo: false,
      selectedRoundNumber: 0,
      apiCallInProcess: false,
      playersConfirmationChangedMap: new Map()
      // players: this.selectedEvent.players
    }
  },
  computed: {
    players () {
      return this.selectedEvent.players
    },
    evenTables () {
      const currentRound = this.selectedEvent.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 === 0)
    },
    oddTables () {
      const currentRound = this.selectedEvent.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 !== 0)
    },
    hideInfoComponents: {
      get () {
        this.$emit('hide-info-components', this.hideEventInfo)
        return this.hideEventInfo
      }
    }
  },
  methods: {
    /**
     * Confirm players on server
     */
    confirmPlayers () {
      // Show user pending request
      this.apiCallInProcess = true
      // Prepare data for request
      const playersToPut = []
      this.playersConfirmationChangedMap.forEach((value, key) => {
        playersToPut.push({
          id: key,
          confirmed: value
        })
      })
      const eventID = this.$store.getters.currentActiveEventID
      this.$http.put(`/events/${eventID}/players/confirmation`, playersToPut)
        .then((response) => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.SUCCESS, text: 'Players are confirmed.'})
        })
        .catch(error => console.log(error))
        .finally(() => { this.apiCallInProcess = false })
    },
    /**
     * Collect only players with changed confirmation to avoid dummy updates on backend
     * @param player with changed confirmation
     */
    confirmPlayerParticipation (player) {
      player.confirmed = !player.confirmed
      // If key already exists => changing to initial state => delete from changes
      if (this.playersConfirmationChangedMap.has(player.id)) {
        this.playersConfirmationChangedMap.delete(player.id)
      } else {
        this.playersConfirmationChangedMap.set(player.id, player.confirmed)
      }
    }
  }
}
</script>
<style scoped>
  .custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }
  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  .selected-row {
    color: var(--v-color-secondary);
    transition: color .3s linear
  }
  .selected-row:not(.on-hover) {
    color: #000000de;
  }
  .on-hover {
    color: #000000de;
    font-weight: bold;
  }
</style>
