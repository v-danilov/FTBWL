<template>
  <v-card class="ma-1">
    <v-card-text style="position: relative">
      <v-row dense align="center" justify="center">
        <v-col cols="2">
          <h3>Стол №{{ pairing.tableNumber }}</h3>
        </v-col>
        <v-col>
          <v-row dense align="center" class="text-center" justify="center">
            <v-col>
              {{ firstUser.firstName }}
              <span>{{ firstUser.nickname }}</span>
              {{ firstUser.lastName }}
            </v-col>
            <v-col
              :style="{ color: $vuetify.theme.currentTheme.additional }"
              cols="2"
            >
              {{ pairing.firstPlayer.tp }}
            </v-col>
            <v-col
              :style="{ color: $vuetify.theme.currentTheme.secondary }"
              cols="2"
            >
              {{ pairing.firstPlayer.vp }}
            </v-col>
          </v-row>
          <v-row dense align="center" class="text-center" justify="center">
            <v-col>
              <v-divider />
            </v-col>
            <v-col
              :style="{ color: $vuetify.theme.currentTheme.additional }"
              cols="2"
              class="text-center"
            >
              TP
            </v-col>
            <v-col
              :style="{ color: $vuetify.theme.currentTheme.secondary }"
              cols="2"
              class="text-center"
            >
              VP
            </v-col>
          </v-row>
          <v-row dense align="center" class="text-center" justify="center">
            <v-col class="text-center">
              {{ secondUser.firstName }}
              <span>{{ secondUser.nickname }} </span>
              {{ secondUser.lastName }}
            </v-col>
            <v-col
              :style="{ color: $vuetify.theme.currentTheme.additional }"
              cols="2"
            >
              {{ pairing.secondPlayer.tp }}
            </v-col>
            <v-col
              :style="{ color: $vuetify.theme.currentTheme.secondary }"
              cols="2"
            >
              {{ pairing.secondPlayer.vp }}
            </v-col>
          </v-row>
        </v-col>
      </v-row>
       <ScoreEdit :pairing="pairing"/>
    </v-card-text>
  </v-card>
</template>
<script>

import ScoreEditComponent from '@/components/event/round/ScoreEditComponent'

export default {
  name: 'TablesInfoComponent',
  components: {
    ScoreEdit: ScoreEditComponent
  },
  props: {
    pairing: {
      type: Object,
      required: true
    }
  },
  computed: {
    // Note that all of these are USERS not PLAYERS
    firstUser () {
      return this.pairing.firstPlayer.user
    },
    secondUser () {
      return this.pairing.secondPlayer.user
    }
  }
}
</script>
