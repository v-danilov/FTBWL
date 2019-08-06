<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-tabs color="primary" dark fixed-tabs slider-color="primary" class="mt-3">
    <v-tab ripple>
      Участники
    </v-tab>
    <v-tab-item>
      <v-card>
        <!-- Header -->
        <v-card-title>
          <v-layout row>
            <v-flex xs1>
              <h2>№</h2>
            </v-flex>
            <v-flex xs3>
              <h2>Игрок</h2>
            </v-flex>
            <v-flex xs2>
              <h2>Фракция</h2>
            </v-flex>
            <v-flex xs1>
              <h2>TP</h2>
            </v-flex>
            <v-flex xs1>
              <h2>VP</h2>
            </v-flex>
            <v-flex xs1>
              <h2>Diff</h2>
            </v-flex>
            <v-flex class="text-xs-center" xs3>
              <h2>Управление</h2>
            </v-flex>
          </v-layout>
        </v-card-title>
        <!-- Body -->
        <v-card-text>
          <v-hover v-slot:default="{ hover }" v-for="(player, index) in players" :key="index">
            <v-layout align-center justify-center row class="selected-row"
                      :class="{ 'on-hover': hover }">
              <v-flex xs1 v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{index + 1}}
              </v-flex>
              <v-flex xs3 v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.name}}
              </v-flex>
              <v-flex xs2 v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.faction}}
              </v-flex>
              <v-flex xs1 v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.tp}}
              </v-flex>
              <v-flex xs1 v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.vp}}
              </v-flex>
              <v-flex xs1 v-bind:class="{ 'disabled-player': player.isCanceled }">
                {{player.diff}}
              </v-flex>
              <v-flex xs3>
                <v-layout align-start justify-center row>
                  <v-flex xs2>
                    <v-btn text icon small @click="players[index].isCanceled = !players[index].isCanceled">
                      <v-icon v-if="!player.isCanceled" color="additional">block</v-icon>
                      <v-icon v-else color="secondary">restore</v-icon>
                    </v-btn>
                  </v-flex>
                  <v-flex xs2>
                    <v-btn text icon small @click="rageQuit(index)">
                      <v-icon color="accent">exit_to_app</v-icon>
                    </v-btn>
                  </v-flex>
                </v-layout>
              </v-flex>
            </v-layout>
          </v-hover>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text outline round color="primary"
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
    <template v-for="(round, index) in rounds">
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
            <v-layout row text-xs-left>
              <v-flex xs3>
                <span>Дата начала: </span> {{round.startDate}}
              </v-flex>
              <v-flex xs3>
                <span>Дата окончания: </span> {{round.endDate}}
              </v-flex>
              <v-spacer></v-spacer>
              <v-flex xs2>
                <span>Статус: </span> {{round.status}}
              </v-flex>
            </v-layout>
            <!--Round parameters -->
            <v-layout row text-xs-left mt-2>
              <v-flex xs1>
                <v-layout column>
                  <v-flex>
                    <span>Расстановка:</span>
                  </v-flex>
                  <v-flex>
                    <span>Стратегия:</span>
                  </v-flex>
                  <v-flex>
                    <span>Схемы:</span>
                  </v-flex>
                </v-layout>
              </v-flex>
              <v-flex>
                <v-layout column>
                  <v-flex>
                    {{round.arrangement}}
                  </v-flex>
                  <v-flex>
                    {{round.strategy}}
                  </v-flex>
                  <v-flex>
                    {{round.schemes}}
                  </v-flex>
                </v-layout>
              </v-flex>
            </v-layout>
          </v-card-text>
        </v-card>
        <v-layout row wrap>
          <!-- Left column with tables -->
          <v-flex xs6>
            <TablesInfoComponent :tables="oddTables" :vuetify-theme="vuetifyTheme"/>
          </v-flex>
          <!-- Right column with tables -->
          <v-flex xs6>
            <TablesInfoComponent :tables="evenTables" :vuetify-theme="vuetifyTheme"/>
          </v-flex>
        </v-layout>
      </v-tab-item>
    </template>
    <v-spacer></v-spacer>
    <v-btn icon @click="hideTournamentInfo = !hideTournamentInfo">
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

export default {
  name: 'TournamentInfoBodyComponent',
  components: {TablesInfoComponent},
  props: {
    rounds: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      hideTournamentInfo: false,
      selectedRoundNumber: 0,
      vuetifyTheme: this.$vuetify.theme,
      playersDataSaving: false,
      players: [
        {
          name: 'Тихомиров \'Витязь\' Борислав',
          faction: 'Arcanists',
          tp: 11,
          vp: 14,
          diff: 28,
          isCanceled: false
        },
        {
          name: 'Кузнецова \'Коралл\' Клара',
          faction: 'Bayou',
          tp: 1,
          vp: 18,
          diff: 18,
          isCanceled: false
        },
        {
          name: 'Трофимова \'Принцесса\' Диана',
          faction: 'Neverborn',
          tp: 20,
          vp: 15,
          diff: 46,
          isCanceled: false
        },
        {
          name: 'Шмидт \'Штирлиц\' Савва',
          faction: 'Outcasts',
          tp: 9,
          vp: 18,
          diff: 39,
          isCanceled: false
        },
        {
          name: 'Троицкий \'Космический\' Галактион',
          faction: 'Resurrectionists',
          tp: 14,
          vp: 14,
          diff: 45,
          isCanceled: false
        },
        {
          name: 'Романова \'Зелёная\' Лиана',
          faction: 'Ten thunders',
          tp: 2,
          vp: 8,
          diff: 34,
          isCanceled: false
        },
        {
          name: 'Тайский \'Острый\' Каллистрат',
          faction: 'The Guild',
          tp: 10,
          vp: 8,
          diff: 43,
          isCanceled: false
        }
      ]
    }
  },
  methods: {
    savePlayers () {
      this.playersDataSaving = true
      this.$http.post(END_POINTS.TOURNAMENT.SAVE_PLAYERS, this.players)
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
    evenTables () {
      let currentRound = this.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 === 0)
    },
    oddTables () {
      let currentRound = this.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 !== 0)
    },
    hideInfoComponents: {
      get () {
        console.log('emmiting')
        this.$emit('hide-info-components', this.hideTournamentInfo)
        return this.hideTournamentInfo
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
