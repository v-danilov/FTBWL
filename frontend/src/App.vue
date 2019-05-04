<template>
  <v-app :style="cssTheme">
    <NavigatorComponent/>
    <v-content>
      <v-container fill-height fluid grid-list-{0} class="pa-0">
        <v-layout row>
          <v-flex>
            <v-container fluid>
              <router-view/>
            </v-container>
          </v-flex>
        </v-layout>
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
      const out = {}

      for (const name of Object.keys(this.$vuetify.theme)) {
        out[`--v-color-${name}`] = this.$vuetify.theme[name]
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
  .text-highlight-primary {
    font-weight: bold;
    color: var(--v-color-primary);
  }

  .primary-color {
    color: var(--v-color-primary);
  }
</style>
