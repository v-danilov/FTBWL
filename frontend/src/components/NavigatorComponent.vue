<template>
  <v-navigation-drawer class="primary"
                       permanent
                       mini-variant
                       :mini-variant-width="100"
                       app>
    <v-container fill-height>
      <v-layout column>
        <!-- User profile button with user name-->
        <v-flex xs1>
          <v-layout column justify-center fill-height>
            <v-flex>
              <!-- TODO WTF is that margin??? -->
              <v-btn large flat icon color="white" class="ml-1" @click="openProfile" >
                <v-icon large>account_circle</v-icon>
              </v-btn>
            </v-flex>
            <v-flex v-if="!!userName" class="help-text text-xs-center">
              <span style="color: white">{{userName}}</span>
            </v-flex>
          </v-layout>
        </v-flex>
        <!-- Navigation buttons -->
        <v-flex xs10>
          <v-layout column justify-center fill-height>
          <v-list
            v-for="(element, index) in navigationElements"
            :key="index" class="mt-5">
            <v-list-tile-action>
              <v-btn large flat icon color="white" @click="navigate(element.path)">
                <v-icon large>{{element.icon}}</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list>
          </v-layout>
        </v-flex>
        <!-- Help button -->
        <v-spacer></v-spacer>
        <v-flex xs1>
          <!-- TODO WTF is that margin??? -->
          <v-btn large flat icon color="white" class="ml-1">
            <v-icon large>help</v-icon>
          </v-btn>
          <v-flex v-if="!!userName" class="help-text text-xs-center">
          </v-flex>
        </v-flex>
      </v-layout>
    </v-container>
  </v-navigation-drawer>
</template>
<script>
export default {
  name: 'NavigatorComponent',
  data () {
    return {
      navigationElements: [
        {path: '/tournaments', name: 'Турниры', icon: 'event_note'},
        {path: '/places', name: 'Площадки', icon: 'place'},
        {path: '/players', name: 'Игроки', icon: 'people'},
        {path: '/ratings', name: 'Рейтинги', icon: 'star'},
        {path: '/statistic', name: 'Статистика', icon: 'bar_chart'}]
      // {path: '/help', name: 'Помощь', icon: 'help'}]
    }
  },
  computed: {
    userName () {
      return this.$store.getters.isAuthenticated ? 'test' : ''
    }
  },
  methods: {
    navigate (path) {
      this.$router.replace(path)
    },
    openProfile () {
      // this.$store.getters.isAuthenticated
      if (this.userName) {
        this.$router.push('/profile')
      } else {
        this.$router.push('SignIn')
      }
    }
  }
}
</script>

<style scoped>
  .help-text {
    margin-top: -10px;
    color: white
  }
</style>
