<template>
  <v-card>
    <v-card-title>
      <span class="label-highlighter">{{selectedTournament.name}} ({{selectedTournament.format.value}})</span>
    </v-card-title>
    <v-card-text>
      <!-- Место, дата, статус -->
      <v-row no-gutters>
        <v-col cols="3">
          <span class=" label-highlighter">Место проведения</span>
        </v-col>
        <v-col cols="3">
          <span class=" label-highlighter">Даты проведения</span>
        </v-col>
        <v-col cols="3">
          <span class=" label-highlighter">Статус</span>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="3">
          <div>{{this.selectedTournament.place.city.name}}, {{this.selectedTournament.place.street}} {{this.selectedTournament.place.building}}</div>
          <a>{{this.selectedTournament.place.name}}</a>
        </v-col>
        <v-col cols="3">
          <div class="">{{this.selectedTournament.timeStart}} - {{this.selectedTournament.timeEnd}}</div>
        </v-col>
        <v-col cols="3">
          <div class="">{{this.selectedTournament.status.value}}</div>
        </v-col>
      </v-row>
      <v-row align="center" justify="center">
        <v-col>
          <v-divider></v-divider>
        </v-col>
      </v-row>
      <!-- Формат, схемпул, количество туров -->
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Формат</span>
        </v-col>
        <v-col cols="3">
          {{ this.selectedTournament.format.value }}
        </v-col>

        <v-col cols="1">
          <span>Схемпул</span>
        </v-col>
        <v-col cols="2">
          {{this.selectedTournament.schemePool || 'Не указан'}}
        </v-col>

        <v-col cols="1">
          <span>Туров</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedTournament.totalTours || 'Не указано' }}
        </v-col>
      </v-row>
      <!-- Участников, стоимость, организатор -->
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Участников</span>
        </v-col>
        <v-col cols="3">
          {{this.selectedTournament.players.length || 'Не известно'}}
        </v-col>

        <v-col cols="1">
          <span>Стоимость</span>
        </v-col>
        <v-col cols="2">
          {{this.selectedTournament.price + '₽' || 'Не указано'}}
        </v-col>

        <v-col cols="1">
          <span>Организатор</span>
        </v-col>
        <v-col>
          {{this.selectedTournament.organizer.nickname}}
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  name: 'TournamentInfoHeaderComponent',
  props: {
    selectedTournament: {}
  }
}
</script>
<style scoped>
  .label-highlighter {
    color: var(--v-color-secondary);
  }
</style>
