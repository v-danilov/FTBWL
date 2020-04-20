<template>
  <v-card>
    <v-card-title>
      <span class="label-highlighter">{{ selectedEvent.name }} ({{ selectedEvent.format.value }})</span>
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
          <div>{{ this.selectedEvent.place.city.name }}, {{ this.selectedEvent.place.street }}
            {{ this.selectedEvent.place.building }}</div>
          <a>{{ this.selectedEvent.place.name }}</a>
        </v-col>
        <v-col cols="3">
          <div class="">{{ this.selectedEvent.timeStart }} - {{ this.selectedEvent.timeEnd }}</div>
        </v-col>
        <v-col cols="3">
          <div class="">{{ this.selectedEvent.status.value }}</div>
        </v-col>
      </v-row>
      <v-row
        align="center"
        justify="center"
      >
        <v-col>
          <v-divider />
        </v-col>
      </v-row>
      <!-- Формат, схемпул, количество туров -->
      <v-row
        dense
        no-gutters
      >
        <v-col cols="1">
          <span>Формат</span>
        </v-col>
        <v-col cols="3">
          {{ this.selectedEvent.format.value }}
        </v-col>

        <v-col cols="1">
          <span>Схемпул</span>
        </v-col>
        <v-col cols="2">
          {{ this.selectedEvent.schemePool || 'Не указан' }}
        </v-col>

        <v-col cols="1">
          <span>Туров</span>
        </v-col>
        <v-col cols="1">
          {{ this.selectedEvent.totalTours || 'Не указано' }}
        </v-col>
      </v-row>
      <!-- Участников, стоимость, организатор -->
      <v-row
        dense
        no-gutters
      >
        <v-col cols="1">
          <span>Участников</span>
        </v-col>
        <v-col cols="3">
          {{ this.selectedEvent.players.length || 'Не известно' }}
        </v-col>

        <v-col cols="1">
          <span>Стоимость</span>
        </v-col>
        <v-col cols="2">
          {{ this.selectedEvent.price + '₽' || 'Не указано' }}
        </v-col>

        <v-col cols="1">
          <span>Организатор</span>
        </v-col>
        <v-col>
          {{ this.selectedEvent.organizer.nickname }}
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  name: 'EventInfoHeaderComponent',
  props: {
    selectedEvent: {}
  }
}
</script>
<style scoped>
  .label-highlighter {
    color: var(--v-color-secondary);
  }
</style>
