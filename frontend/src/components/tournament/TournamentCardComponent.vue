<template>
  <v-hover>
    <v-card class="mt-3 mx-auto" slot-scope="{ hover }"
            :class="`elevation-${hover ? 12 : 2}`">
      <v-card-title>
        <h2 class="mr-2">{{tournament.name}}</h2>
        <v-icon :style="{color : statusInfo.color}" class="mr-1">{{statusInfo.icon}}</v-icon>
        <span :style="{color : statusInfo.color}">{{tournament.status}}</span>
        <v-spacer></v-spacer>
        <h2 :style="{color : themeColors.secondary}">{{tournament.price}} ₽</h2>
      </v-card-title>
      <v-card-title>
        <v-layout row wrap>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span>Где: </span>
            <a href="#" :style="{color : themeColors.secondary}">{{tournament.place}}</a>
          </v-flex>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span>Организатор:</span>
            <a href="#" :style="{color : themeColors.secondary}">{{tournament.organizer}}</a>
          </v-flex>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span>Когда: </span>
            <template v-if="!! tournament.startDate">
              {{tournament.startDate}} -
            </template>
            {{tournament.endDate}}
          </v-flex>
          <v-flex xs5 sm5 md5 class="text-xs-left text-sm-left text-md-left">
            <span>Формат: </span>
            {{tournament.format}}
          </v-flex>
          <v-flex xs1 sm1 md1 class="text-xs-right text-sm-right text-md-right">
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon :color="themeColors.primary" large>assignment_turned_in</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </v-card-title>
      <!-- <v-card-actions>
         <v-spacer></v-spacer>
         <v-icon large>assignment_turned_in</v-icon>
       </v-card-actions>-->
    </v-card>
  </v-hover>
</template>
<script>
import statusColorize from '../util/statusIconWithColor'

export default {
  name: 'TournamentCardComponent',
  props: {
    tournament: Object,
    required: true
  },
  data () {
    return {
      themeColors: this.$vuetify.theme
    }
  },
  methods: {
    openTournamentInfo () {
      console.log(this.tournament)
    }
  },
  computed: {
    statusInfo () {
      return statusColorize(this.tournament.status)
    }
  }
}
</script>
<style scoped>
</style>
