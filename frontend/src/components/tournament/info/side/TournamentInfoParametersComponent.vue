<template>
  <v-card class="mt-2">
    <v-card-text>
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Формат </span>
        </v-col>
        <v-col cols="1">
          {{ this.selectedTournament.format }}
        </v-col>

        <v-col cols="1">
          <span>Схемпул</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedTournament.schemePool}}
        </v-col>

        <v-col cols="1">
          <span>Количество туров</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedTournament.roundsNumber || 'Не указано' }}
        </v-col>
      </v-row>
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Участников</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedTournament.participantsNumber || 'Не известно'}}
        </v-col>

        <v-col cols="1">
          <span>Статус турнира</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedTournament.status}}
        </v-col>

        <v-col cols="1">
          <span>Организатор</span>
        </v-col>
        <v-col>
          {{this.selectedTournament.organizer}}
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  name: 'TournamentInfoParametersComponent',
  props: {
    selectedTournament: {}
  }
}
</script>
