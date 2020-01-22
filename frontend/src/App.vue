<template>
  <v-app :style="cssTheme">
    <div v-if="storeIsReady">
      <NavigatorComponent/>
      <v-content>
        <v-container class="fill-height grid-list-{0}" fluid>
          <v-row class="fill-height">
            <v-col>
              <router-view/>
            </v-col>
          </v-row>
        </v-container>
      </v-content>
    </div>
    <div v-else>
      Loading...
    </div>
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
    },
    storeIsReady () {
      return this.$store.getters.dictsLoadedFlag
    }
  },
  methods: {
    initStoreValues () {
      // Initialize game system cache first
      this.$store.dispatch(ACTIONS.CACHE_INIT.GAME_SYSTEMS, END_POINTS.GET_ALL.GAME_SYSTEMS).then(gameSystems => {
        // Initialize dictionary cache after that
        let malifauxID = this.$store.getters.cachedGameSystems.get('MAL').id // todo cache by selected game system
        console.log('id: ' + malifauxID)
        this.$store.dispatch(ACTIONS.CACHE_INIT.ALL_DICTS, END_POINTS.GET_ALL.DICTS_BY_GAME_SYSTEM + malifauxID)
      })
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
