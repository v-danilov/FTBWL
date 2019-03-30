<template>
  <v-hover>
    <v-card class="mt-3 mx-auto" slot-scope="{ hover }"
            :class="`elevation-${hover ? 12 : 2}`">
      <v-card-title>
        <h2 :style="{color : themeColors.primary}" class="mr-2">{{tournament.name}}</h2>
        <v-icon :style="{color : statusInfo.color}" class="mr-1">{{statusInfo.icon}}</v-icon>
        <span :style="{color : statusInfo.color}">{{tournament.status}}</span>
        <v-spacer></v-spacer>
        <h2 h2 :style="{color : themeColors.secondary}">{{tournament.price}} ₽</h2>
      </v-card-title>
      <v-card-title>
        <v-layout row wrap>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span :style="spanPrefixStyle">Где: </span>
            <a href="#" :style="{color : themeColors.secondary}">{{tournament.place}}</a>
          </v-flex>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span :style="spanPrefixStyle">Организатор:</span>
            <a href="#" :style="{color : themeColors.secondary}">{{tournament.organizer}}</a>
          </v-flex>
          <v-flex xs6 sm6 md6 class="text-xs-left text-sm-left text-md-left">
            <span :style="spanPrefixStyle">Когда: </span>
            <template v-if="!! tournament.startDate">
              {{tournament.startDate}} -
            </template>
            {{tournament.endDate}}
          </v-flex>
          <v-flex xs5 sm5 md5 class="text-xs-left text-sm-left text-md-left">
            <span :style="spanPrefixStyle">Формат: </span>
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
      if (this.tournament.status === 'Запланирован') {
        return {icon: 'query_builder', color: this.themeColors.additional}
      }
      if (this.tournament.status === 'Открыта регистрация') {
        return {icon: 'done', color: this.themeColors.secondary}
      }
      if (this.tournament.status === 'Окончен') {
        return {icon: 'done_all', color: this.themeColors.accent}
      }
    },
    spanPrefixStyle () {
      return {'font-weight': 'bold', color: this.themeColors.primary}
    }
  }
}
</script>
<style scoped>
</style>
