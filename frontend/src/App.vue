<template>
  <v-app>
    <v-content app>
      <v-container fill-height fluid grid-list-{0} class="pa-0">
        <v-layout row>
          <v-flex xs1 sm1 md1>
            <NavigatorComponent/>
          </v-flex>
          <v-flex xs11 sm11 md11>
          <router-view/>
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
  beforeMount () {
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
</style>
