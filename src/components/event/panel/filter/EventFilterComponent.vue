<template>
  <v-card>
    <v-card-text>
      <v-row
        dense
        justify="space-around"
      >
        <!-- Формат -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <FilterSelectorComponent
            :data-array="this.$store.getters.cachedFormats"
            :selected-elements="filterFormParams.formats"
            ref="formatSelector"
            label="Формат"
            main-property="value"
          />
        </v-col>
        <!-- Страна -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <FilterSelectorComponent
            :data-array="this.$store.getters.cachedCountries"
            :selected-elements="filterFormParams.countries"
            ref="countrySelector"
            label="Страна"
            main-property="name"
          />
        </v-col>
        <!-- Город -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <FilterSelectorComponent
            v-model="filterFormParams.cities"
            :data-array="this.$store.getters.cachedCities"
            :selected-elements="filterFormParams.cities"
            ref="citySelector"
            label="Город"
            main-property="name"
          />
        </v-col>
        <!-- Площадка -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <FilterSelectorComponent
            :data-array="this.$store.getters.cachedPlaces"
            :selected-elements="filterFormParams.places"
            ref="placeSelector"
            label="Площадка"
            short-lable="Площ."
            main-property="name"
          />
        </v-col>
        <!-- Организатор -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <FilterSelectorComponent
            :data-array="this.$store.getters.cachedOrganizers"
            v-model="filterFormParams.organizers"
            ref="organizerSelector"
            label="Организатор"
            short-lable="Орг."
            main-property="alias"
          />
        </v-col>
      </v-row>
      <v-row
        dense
        justify="space-around"
      >
        <!-- Название -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <v-text-field
            v-model="filterFormParams.name"
            label="Название"
            placeholder=""
          />
        </v-col>
        <!-- Количество дней -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <v-select
            v-model="filterFormParams.daysNumber"
            :items="['1 день', '2 дня']"
            label="Кол-во дней"
            clearable
          />
        </v-col>
        <!-- Дата начала -->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <v-menu
            v-model="menuStartDate"
            :close-on-content-click="false"
            ref="menuStartDate"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="formattedDateStart"
                v-on="on"
                label="Дата начала"
                readonly
              />
            </template>
            <v-date-picker
              v-model="filterFormParams.startDate"
              @input="menuEndDate = false"
              no-title
              scrollable
              color="secondary"
            >
              <v-spacer />
              <v-btn
                @click="menuStartDate = false"
                rounded
                text
                color="accent"
              >
                Отмена
              </v-btn>
              <v-btn
                @click="$refs.menuStartDate.save(filterFormParams.startDate)"
                rounded
                text
                color="secondary"
              >
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
        </v-col>
        <!-- Дата окончания-->
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <v-menu
            v-model="menuEndDate"
            :close-on-content-click="false"
            ref="menuEndDate"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="formattedDateEnd"
                v-on="on"
                label="Дата окончания"
                readonly
              />
            </template>
            <v-date-picker
              v-model="filterFormParams.endDate"
              @input="menuEndDate = false"
              no-title
              scrollable
              color="secondary"
            >
              <v-spacer />
              <v-btn
                @click="menuEndDate = false"
                rounded
                text
                color="accent"
              >
                Отмена
              </v-btn>
              <v-btn
                @click="$refs.menuEndDate.save(filterFormParams.endDate)"
                rounded
                text
                color="secondary"
              >
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
        </v-col>
        <v-col
          cols="6"
          sm="4"
          md="2"
        >
          <FilterSelectorComponent
            :data-array="this.$store.getters.cachedStatuses"
            :selected-elements="filterFormParams.statuses"
            ref="statusSelector"
            label="Статус"
            main-property="value"
          />
        </v-col>
      </v-row>
    </v-card-text>
    <v-card-actions>
      <v-btn
        @click="resetFilterForm"
        rounded
        text
        color="accent"
      >Сбросить</v-btn>
      <v-btn
        rounded
        color="primary"
      >Применить</v-btn>
      <v-spacer />
      <v-btn
        @click="$emit('close-event-filter')"
        text
        icon
        color="accent"
      >
        <v-icon>close</v-icon>
      </v-btn>
    </v-card-actions>
    <v-spacer />
  </v-card>
</template>
<script>
import FilterSelectorComponent from './FilterSelectorComponent'

export default {
  name: 'EventFilterComponent',
  components: { FilterSelectorComponent },
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
  computed: {
    formattedDateStart () {
      return this.formatDate(this.filterFormParams.startDate)
    },
    formattedDateEnd () {
      return this.formatDate(this.filterFormParams.endDate)
    }
  },
  methods: {
    resetFilterForm () {
      for (const refName in this.$refs) {
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
  }
}
</script>
