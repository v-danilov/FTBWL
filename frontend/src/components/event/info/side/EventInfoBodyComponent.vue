<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-tabs slider-color="additional" background-color="primary" fixed-tabs class="mt-3">
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
            <v-col class="text-center" cols="1">
              <span>TP</span>
            </v-col>
            <v-col class="text-center" cols="1">
              <span>VP</span>
            </v-col>
            <v-col class="text-center" cols="1">
              <span>Diff</span>
            </v-col>
            <v-col class="text-center" cols="3">
              <span>Управление</span>
            </v-col>
          </v-row>
        </v-card-title>
        <!-- Body -->
        <v-card-text>
          <v-hover v-slot:default="{ hover }" v-for="(player, index) in players" :key="index">
            <v-row class="selected-row" dense :class="{ 'on-hover': hover }" justify="center">
              <v-col cols="1" v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{index + 1}}
              </v-col>
              <v-col cols="3" v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.name}}
              </v-col>
              <v-col cols="2" v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.faction.systemName}}
              </v-col>
              <v-col cols="1" class="text-center" v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.tp}}
              </v-col>
              <v-col class="text-center" cols="1" v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.vp}}
              </v-col>
              <v-col cols="1" class="text-center" v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.diff}}
              </v-col>
              <v-col class="text-center" cols="3">
                <v-btn text icon small class="mr-4"
                       @click="players[index].isCanceled = !players[index].isCanceled">
                  <v-icon v-if="!player.isCanceled" color="additional">block</v-icon>
                  <v-icon v-else color="secondary">restore</v-icon>
                </v-btn>
                <v-btn text icon small @click="rageQuit(index)">
                  <v-icon color="accent">exit_to_app</v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </v-hover>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text outlined rounded color="primary"
                 :loading="playersDataSaving"
                 :disabled="playersDataSaving"
                 @click="savePlayers">
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
      <v-tab :key="index"
             ripple
             @change="selectedRoundNumber = index">
        Раунд {{index + 1}}
      </v-tab>
      <v-tab-item :key="index">
        <!-- Round info card -->
        <v-card>
          <v-card-text>
            <!-- Round status -->
            <v-row no-gutters>
              <v-col>
                <span>Дата начала:</span>
              </v-col>
              <v-col>
                {{round.startDate}}
              </v-col>
              <v-col>
                <span>Дата окончания:</span>
              </v-col>
              <v-col>
                {{round.endDate}}
              </v-col>
              <v-col>
                <span>Статус: </span>
              </v-col>
              <v-col>
                {{round.status}}
              </v-col>
            </v-row>
            <!--Round parameters -->
            <v-row no-gutters>
              <v-col>
                <span>Расстановка:</span>
              </v-col>
              <v-col>{{round.arrangement}}</v-col>

              <v-col>
                <span>Стратегия:</span>
              </v-col>
              <v-col>{{round.strategy}}</v-col>

              <v-col>
                <span>Стратегия:</span>
              </v-col>
              <v-col>{{round.schemes}}</v-col>
            </v-row>
            <v-row no-gutters>
              <v-col>
                <v-btn rounded color="additional" class="white--text"
                       @click="roundSettingDialog = !roundSettingDialog">
                  Начать раунд
                </v-btn>
                <RoundSettingsComponent v-if="roundSettingDialog"
                                        :round-number="index"
                                        :round-data="round"></RoundSettingsComponent>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
        <v-row>
          <!-- Left column with tables -->
          <v-col cols="4" v-for="(table, index) in selectedEvent.rounds[selectedRoundNumber].tables" :key="index">
            <TablesInfoComponent :table="table"/>
          </v-col>
          <!-- Right column with tables -->
          <!--<TablesInfoComponent :tables="evenTables"/>-->
        </v-row>
      </v-tab-item>
    </template>
    <v-spacer></v-spacer>
    <v-btn icon @click="hideEventInfo = !hideEventInfo">
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
import TablesInfoComponent from './TablesInfoComponent'
import {END_POINTS} from '../../../util/constants/EndPointsConstants'
import RoundSettingsComponent from '../../round/RoundSettingsComponent'

export default {
  name: 'EventInfoBodyComponent',
  components: {RoundSettingsComponent, TablesInfoComponent},
  props: {
    selectedEvent: {}
  },
  data () {
    return {
      vuetifyTheme: this.$vuetify.theme,
      hideEventInfo: false,
      roundSettingDialog: false,
      selectedRoundNumber: 0,
      playersDataSaving: false
      // players: this.selectedEvent.players
    }
  },
  methods: {
    savePlayers () {
      this.playersDataSaving = true
      this.$http.post(END_POINTS.EVENTS.SAVE_PLAYERS, this.players)
    },
    cancelReg (index) {
      this.players[index].isCanceled = true
    },
    rageQuit (index) {
      // TODO endponit call
      // if success - delete from UI
    }
  },
  computed: {
    players () {
      return this.selectedEvent.players
    },
    evenTables () {
      let currentRound = this.selectedEvent.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 === 0)
    },
    oddTables () {
      let currentRound = this.selectedEvent.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 !== 0)
    },
    hideInfoComponents: {
      get () {
        this.$emit('hide-info-components', this.hideEventInfo)
        return this.hideEventInfo
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
  .disabled-player {
    color: var(--v-color-accent);
    text-decoration: line-through
  }
  .selected-row {
    color: var(--v-color-secondary);
    transition: color .3s linear
  }
  .selected-row:not(.on-hover) {
    color: #000000de;
  }
</style>
