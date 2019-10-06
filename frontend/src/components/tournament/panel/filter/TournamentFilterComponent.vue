<template>
    <v-card>
      <v-card-text>
        <v-row dense justify="space-around">
          <!-- Формат -->
          <v-col cols="6" sm="4" md="2">
            <FilterSelectorComponent
              ref="formatSelector"
              label="Формат"
              main-property="name"
              :data-array=this.$store.getters.cachedFormats
              :selected-elements="filterFormParams.formats"/>
          </v-col>
          <!-- Страна -->
          <v-col cols="6" sm="4" md="2">
            <FilterSelectorComponent
              ref="countrySelector"
              label="Страна"
              main-property="name"
              :data-array=this.$store.getters.cachedCountries
              :selected-elements="filterFormParams.countries"/>
          </v-col>
          <!-- Город -->
          <v-col cols="6" sm="4" md="2">
            <FilterSelectorComponent
              ref="citySelector"
              v-model="filterFormParams.cities"
              label="Город"
              main-property="name"
              :data-array=this.$store.getters.cachedCities
              :selected-elements="filterFormParams.cities"/>
          </v-col>
          <!-- Площадка -->
          <v-col cols="6" sm="4" md="2">
            <FilterSelectorComponent
              ref="placeSelector"
              label="Площадка"
              short-lable="Площ."
              main-property="name"
              :data-array=this.$store.getters.cachedPlaces
              :selected-elements="filterFormParams.places"/>
          </v-col>
          <!-- Организатор -->
          <v-col cols="6" sm="4" md="2">
            <FilterSelectorComponent
              ref="organizerSelector"
              label="Организатор"
              short-lable="Орг."
              main-property="alias"
              :data-array=this.$store.getters.cachedOrganizers
              v-model="filterFormParams.organizers"
              />
          </v-col>
        </v-row>
        <v-row dense justify="space-around">
          <!-- Название -->
          <v-col cols="6" sm="4" md="2">
            <v-text-field
              label="Название"
              placeholder=""
              v-model="filterFormParams.name"
            >
            </v-text-field>
          </v-col>
          <!-- Количество дней -->
          <v-col cols="6" sm="4" md="2">
            <v-select label="Кол-во дней"
                      v-model="filterFormParams.daysNumber"
                      clearable
                      :items="['1 день', '2 дня']">
            </v-select>
          </v-col>
          <!-- Дата начала -->
          <v-col cols="6" sm="4" md="2">
            <v-menu
              ref="menuStartDate"
              v-model="menuStartDate"
              :close-on-content-click="false"
              lazy
              transition="scale-transition"
              offset-y
              full-width
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="formattedDateStart"
                  label="Дата начала"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="filterFormParams.startDate"
                             no-title
                             scrollable
                             color="secondary"
                             @input="menuEndDate = false">
                <v-spacer></v-spacer>
                <v-btn rounded text color="accent" @click="menuStartDate = false">
                  Отмена
                </v-btn>
                <v-btn rounded text color="secondary"
                       @click="$refs.menuStartDate.save(filterFormParams.startDate)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <!-- Дата окончания-->
          <v-col cols="6" sm="4" md="2">
            <v-menu
              ref="menuEndDate"
              v-model="menuEndDate"
              :close-on-content-click="false"
              lazy
              transition="scale-transition"
              offset-y
              full-width
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="formattedDateEnd"
                  label="Дата окончания"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="filterFormParams.endDate"
                             no-title
                             scrollable
                             color="secondary"
                             @input="menuEndDate = false">
                <v-spacer></v-spacer>
                <v-btn rounded text color="accent"
                       @click="menuEndDate = false">
                  Отмена
                </v-btn>
                <v-btn rounded text color="secondary"
                       @click="$refs.menuEndDate.save(filterFormParams.endDate)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="6" sm="4" md="2">
            <FilterSelectorComponent
              ref="statusSelector"
              label="Статус"
              main-property="desc"
              :data-array=this.$store.getters.cachedStatuses
              :selected-elements="filterFormParams.statuses"></FilterSelectorComponent>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-btn rounded text color="accent" @click="resetFilterForm">Сбросить</v-btn>
        <v-btn rounded color="primary">Применить</v-btn>
        <v-spacer></v-spacer>
        <v-btn text icon color="accent"
               @click="$emit('close-tournament-filter')">
          <v-icon>close</v-icon>
        </v-btn>
      </v-card-actions>
      <v-spacer></v-spacer>
    </v-card>
</template>
<script>
import FilterSelectorComponent from './FilterSelectorComponent'

export default {
  name: 'TournamentFilterComponent',
  components: {FilterSelectorComponent},
  data () {
    return {
      menuStartDate: false,
      menuEndDate: false,
      filterFormParams: {
        name: '',
        formats: [],
        countries: [],
        cities: [],
        places: [],
        organizers: [],
        statuses: [],
        daysNumber: 0,
        startDate: null,
        endDate: null
      }
    }
  },
  methods: {
    resetFilterForm () {
      for (let refName in this.$refs) {
        if (refName.toString().endsWith('Selector')) {
          this.$refs[refName].resetSelected()
        }
      }
      this.filterFormParams = {
        name: '',
        formats: [],
        countries: [],
        cities: [],
        places: [],
        organizers: [],
        statuses: [],
        daysNumber: 0,
        dateStart: null,
        dateEnd: null
      }
    },
    formatDate (date) {
      if (!date) return null

      const [year, month, day] = date.split('-')
      return `${day}.${month}.${year}`
    }
  },
  computed: {
    formattedDateStart () {
      return this.formatDate(this.filterFormParams.startDate)
    },
    formattedDateEnd () {
      return this.formatDate(this.filterFormParams.endDate)
    }
  }
}
</script>
