<template>
  <v-card>
    <v-card-title>
      <span class="lable-highlighter">{{selectedTournament.name}} ({{selectedTournament.format}})</span>
    </v-card-title>
    <v-card-text>
      <!-- Место, дата, статус -->
      <v-row dense>
        <v-col cols="3">
          <span class=" lable-highlighter">Место проведения</span>
        </v-col>
        <v-col cols="3">
          <span class=" lable-highlighter">Даты проведения</span>
        </v-col>
        <v-col cols="3">
          <span class=" lable-highlighter">Статус</span>
        </v-col>
      </v-row>
      <v-row dense>
        <v-col cols="3">
          <div class="">{{this.selectedTournament.place}}</div>
        </v-col>
        <v-col cols="3">
          <div class="">{{this.selectedTournament.date}}</div>
        </v-col>
        <v-col cols="3">
          <div class="">{{this.selectedTournament.status}}</div>
        </v-col>
      </v-row>
      <!--<v-row dense>
        <v-col cols="1">
          <span class="title lable-highlighter">Место проведения</span>
        </v-col>
        <v-col cols="3">
          <div class="title">{{this.selectedTournament.place}}</div>
        </v-col>
        <v-col cols="1">
          <span class="title lable-highlighter">Даты проведения</span>
        </v-col>
        <v-col cols="2">
          <div class="title">{{this.selectedTournament.date}}</div>
        </v-col>
        <v-col cols="1">
          <span class="title lable-highlighter">Статус</span>
        </v-col>
        <v-col>
          <div class="title">{{this.selectedTournament.status}}</div>
        </v-col>
      </v-row>-->
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
          {{ this.selectedTournament.format }}
        </v-col>

        <v-col cols="1">
          <span>Схемпул</span>
        </v-col>
        <v-col cols="2">
          {{this.selectedTournament.schemePool}}
        </v-col>

        <v-col cols="1">
          <span>Количество туров</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedTournament.roundsNumber || 'Не указано' }}
        </v-col>
      </v-row>
      <!-- Участников, стоимость, организатор -->
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Участников</span>
        </v-col>
        <v-col cols="3">
          {{this.selectedTournament.participantsNumber || 'Не известно'}}
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
          {{this.selectedTournament.organizer}}
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
  .lable-highlighter {
    color: var(--v-color-secondary);
  }
</style>
