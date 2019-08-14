<template>
  <v-app :style="cssTheme">
    <NavigatorComponent/>
    <v-content>
      <v-container fluid class="fill-height grid-list-{0}">
        <v-row class="fill-height">
          <v-col>
            <router-view/>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import NavigatorComponent from './components/NavigatorComponent'
import {END_POINTS} from './components/util/constants/EndPointsConstants'
import {ACTIONS} from './components/util/constants/ActionConstants'

export default {
  name: 'App',
  components: {NavigatorComponent},
  computed: {
    cssTheme () {
      const currentVTheme = this.$vuetify.theme.currentTheme
      const out = {}
      for (const name of Object.keys(currentVTheme)) {
        out[`--v-color-${name}`] = currentVTheme[name]
      }
      return out
    }
  },
  methods: {
    initStoreValues () {
      this.$store.dispatch(ACTIONS.CACHE_INIT.FORMATS, END_POINTS.GET_ALL.FORMATS)
      this.$store.dispatch(ACTIONS.CACHE_INIT.COUNTRIES, END_POINTS.GET_ALL.COUNTRIES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.CITIES, END_POINTS.GET_ALL.CITIES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.PLACES, END_POINTS.GET_ALL.PLACES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.ORGANIZERS, END_POINTS.GET_ALL.ORGANIZERS)
      this.$store.dispatch(ACTIONS.CACHE_INIT.STATUSES, END_POINTS.GET_ALL.STATUSES)
      this.$store.dispatch(ACTIONS.CACHE_INIT.FRACTIONS, END_POINTS.GET_ALL.FRACTIONS)
    }
  },

  created () {
    this.initStoreValues()
    // Handle browser "go back" button
    window.popStateDetected = false
    window.addEventListener('popstate', () => {
      window.popStateDetected = true
      console.log('browser-back-button')
    })
  }
}
</script>

<style>
  span {
    font-weight: bold;
    color: var(--v-color-primary);
  }

  h1, h2, h3, h4, h5, h6 {
    color: var(--v-color-primary);
  }
</style>
