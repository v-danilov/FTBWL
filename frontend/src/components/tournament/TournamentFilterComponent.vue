<template>
    <v-card v-if="filterIsActive">
      <v-card-title>
        <v-layout row wrap justify-space-around>
          <v-flex xs6 sm4 md2>
            <FilterSelectorComponent
              label="Формат"
              main-property="name"
              :data-array=this.$store.getters.cachedFormats
              :selected-elements="filterFormParams.formats"></FilterSelectorComponent>
          </v-flex>
          <v-flex xs6 sm4 md2>
            <FilterSelectorComponent
              label="Страна"
              main-property="name"
              :data-array=this.$store.getters.cachedCountries
              :selected-elements="filterFormParams.countries"></FilterSelectorComponent>
          </v-flex>
          <v-flex xs6 sm4 md2>
            <FilterSelectorComponent
              v-model="filterFormParams.cities"
              label="Город"
              main-property="name"
              :data-array=this.$store.getters.cachedCities
              :selected-elements="filterFormParams.cities"></FilterSelectorComponent>
          </v-flex>
          <v-flex xs6 sm4 md2>
            <FilterSelectorComponent
              label="Площадка"
              main-property="name"
              :data-array=this.$store.getters.cachedPlaces
              :selected-elements="filterFormParams.places"></FilterSelectorComponent>
          </v-flex>
          <v-flex xs6 sm4 md2>
            <FilterSelectorComponent
              label="Организатор"
              main-property="alias"
              :data-array=this.$store.getters.cachedOrganizers
              v-model="filterFormParams.organizers"
              ></FilterSelectorComponent>
          </v-flex>
        </v-layout>
      </v-card-title>
      <v-card-title>
        <v-layout row wrap justify-space-around>
          <v-flex xs6 sm4 md2>
            <v-text-field
              block
              solo
              label="Название"
              placeholder=""
              v-model="filterFormParams.name"
            >
            </v-text-field>
          </v-flex>

          <v-flex xs6 sm4 md2>
            <v-select solo label="Кол-во дней"
                      v-model="filterFormParams.daysNumber"
                      clearable
                      :items="[1, 2]">
            </v-select>
          </v-flex>

          <v-flex xs6 sm4 md2>
            <v-text-field solo label="Дата1" placeholder="Дата1"
                          v-model="filterFormParams.dateStart">
            </v-text-field>
          </v-flex>
          <v-flex xs6 sm4 md2>
            <v-text-field solo label="Дата2" placeholder="Дата2"
                          v-model="filterFormParams.dateEnd">
            </v-text-field>
          </v-flex>
          <v-flex xs6 sm4 md2>
            <FilterSelectorComponent
              label="Статус"
              main-property="desc"
              :data-array=this.$store.getters.cachedStatuses
              :selected-elements="filterFormParams.statuses"></FilterSelectorComponent>
          </v-flex>
        </v-layout>
      </v-card-title>
      <v-card-actions>
        <v-btn round flat color="primary" @click="resetFilterForm">Сбросить</v-btn>
        <v-btn round color="primary">Применить</v-btn>
        <v-spacer></v-spacer>
        <v-btn flat icon color="primary"
               @click="filterIsActive = !filterIsActive">
          <v-icon>close</v-icon>
        </v-btn>
      </v-card-actions>
      <v-spacer></v-spacer>
    </v-card>
    <v-btn v-else round color="primary"
           @click="filterIsActive = !filterIsActive">
      <v-icon large>search</v-icon>
      Открыть фильтр
    </v-btn>
</template>
<script>
import {ACTIONS} from '../util/constants/ActionConstants'
import {END_POINTS} from '../util/constants/EndPointsConstants'
import FilterSelectorComponent from './FilterSelectorComponent'

export default {
  name: 'TournamentFilterComponent',
  components: {FilterSelectorComponent},
  data () {
    return {
      message: 'helo',
      filterIsActive: false,
      filterFormParams: {
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
    }
  },
  methods: {
    resetFilterForm () {
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
    initStoreValues () {
      this.$store.dispatch(ACTIONS.CACHE_INIT.FORMATS, END_POINTS.GET_ALL.FORMATS)
      this.$store.dispatch(ACTIONS.CACHE_INIT.COUNTRIES, END_POINTS.GET_ALL.COUNTRIES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.CITIES, END_POINTS.GET_ALL.CITIES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.PLACES, END_POINTS.GET_ALL.PLACES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.ORGANIZERS, END_POINTS.GET_ALL.ORGANIZERS)
      this.$store.dispatch(ACTIONS.CACHE_INIT.STATUSES, END_POINTS.GET_ALL.STATUSES)
    }
  },
  beforeMount () {
    this.initStoreValues()
  }
}
</script>
