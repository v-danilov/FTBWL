<template>
  <v-app  :style="cssTheme">
    <NavigatorComponent/>
    <v-content>
      <v-container fill-height fluid grid-list-{0} class="pa-0">
        <v-layout row>
          <v-flex>
            <v-container fluid >
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
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
}
span {
  font-weight: bold;
  color: var(--v-color-primary);
}
h1, h2, h3, h4, h5, h6 {
  color: var(--v-color-primary);
}
</style>
