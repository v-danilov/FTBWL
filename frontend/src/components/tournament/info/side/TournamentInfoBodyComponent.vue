<template>
  <v-tabs color="primary" dark fixed-tabs slider-color="primary" class="mt-3">
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
        <v-layout row  wrap>
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
  </v-tabs>
</template>

<script>
import TablesInfoComponent from './TablesInfoComponent'

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
      selectedRoundNumber: 0,
      vuetifyTheme: this.$vuetify.theme
    }
  },
  methods: {},
  computed: {
    evenTables () {
      let currentRound = this.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 === 0)
    },
    oddTables () {
      let currentRound = this.rounds[this.selectedRoundNumber]
      return currentRound.tables.filter(table => table.tableNumber % 2 !== 0)
    }
  }
}
</script>
