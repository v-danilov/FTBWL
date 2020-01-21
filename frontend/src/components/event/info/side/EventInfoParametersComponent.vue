<template>
  <v-card class="mt-2">
    <v-card-text>
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Формат </span>
        </v-col>
        <v-col cols="1">
          {{ this.selectedEvent.format }}
        </v-col>

        <v-col cols="1">
          <span>Схемпул</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedEvent.schemePool}}
        </v-col>

        <v-col cols="1">
          <span>Количество туров</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedEvent.roundsNumber || 'Не указано' }}
        </v-col>
      </v-row>
      <v-row dense no-gutters>
        <v-col cols="1">
          <span>Участников</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedEvent.participantsNumber || 'Не известно'}}
        </v-col>

        <v-col cols="1">
          <span>Статус турнира</span>
        </v-col>
        <v-col cols="1">
          {{this.selectedEvent.status}}
        </v-col>

        <v-col cols="1">
          <span>Организатор</span>
        </v-col>
        <v-col>
          {{this.selectedEvent.organizer}}
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  name: 'EventInfoParametersComponent',
  props: {
    selectedEvent: {}
  }
}
</script>
