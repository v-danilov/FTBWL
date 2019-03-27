<template>
  <v-card class="mt-3">
    <v-card-title>
      <h2 :style="{color : this.$vuetify.theme.primary}" class="mr-2">{{tournament.name}}</h2>
      <v-icon :style="{color : statusInfo.color}" class="mr-1">{{statusInfo.icon}}</v-icon>
      <span :style="{color : statusInfo.color}">{{tournament.status}}</span>
      <v-spacer></v-spacer>
      <h2 h2 :style="{color : this.$vuetify.theme.primary}">{{tournament.price}} ₽</h2>
    </v-card-title>
    <v-card-title>
        <v-layout row wrap justify-end class="text-md-left">
          <v-flex md6>
            <span :style="spanPrefixStyle">Где: </span>
            <a href="#">{{tournament.place}}</a>
          </v-flex>
          <v-flex md6>
            <span :style="spanPrefixStyle">Организатор:
            </span> <a href="#">{{tournament.organizer}}</a>
          </v-flex>
          <v-flex md6>
            <span :style="spanPrefixStyle">Когда: </span>
            <template v-if="!! tournament.startDate">
              {{tournament.startDate}} -
            </template>
            {{tournament.endDate}}
          </v-flex>
          <v-flex md6>
            <span :style="spanPrefixStyle">Формат: </span>
            {{tournament.format}}
          </v-flex>
        </v-layout>
    </v-card-title>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-icon large>assignment_turned_in</v-icon>
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  props: {
    tournament: Object,
    required: true
  },
  name: 'TournamentCardComponent',
  methods: {},
  computed: {
    statusInfo () {
      if (this.tournament.status === 'Запланирован') {
        return {icon: 'query_builder', color: this.$vuetify.theme.primary}
      }
      if (this.tournament.status === 'Открыта регистрация') {
        return {icon: 'done', color: this.$vuetify.theme.primary}
      }
      if (this.tournament.status === 'Окончен') {
        return {icon: 'done_all', color: this.$vuetify.theme.secondary}
      }
    },
    spanPrefixStyle () {
      return {'font-weight': 'bold', color: this.$vuetify.theme.primary}
    }
  }
}
</script>
<style scoped>
</style>
