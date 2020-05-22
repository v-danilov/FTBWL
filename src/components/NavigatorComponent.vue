<template>
  <v-navigation-drawer
    :mini-variant-width="70"
    class="primary"
    permanent
    mini-variant
    app
  >
    <v-list nav>
      <v-list-item @click="openProfile()">
        <v-list-item-content>
          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <v-icon large color="white" v-on="on">{{userProfileIcon}}</v-icon>
            </template>
            Профиль
          </v-tooltip>
        </v-list-item-content>
      </v-list-item>

      <v-list-item @click="navigate('/events')">
        <v-list-item-content>
           <v-tooltip right>
            <template v-slot:activator="{ on }">
              <v-icon large color="white" v-on="on">event_note</v-icon>
            </template>
            Турниры
          </v-tooltip>
        </v-list-item-content>
      </v-list-item>

      <v-list-item @click="navigate('/place')">
        <v-list-item-content>
          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <v-icon large color="white" v-on="on">place</v-icon>
            </template>
            Площадки
          </v-tooltip>
        </v-list-item-content>
      </v-list-item>

      <v-list-item @click="navigate('/raiting')">
        <v-list-item-content>
           <v-tooltip right>
            <template v-slot:activator="{ on }">
              <v-icon large color="white" v-on="on">star</v-icon>
            </template>
            Рейтинг
          </v-tooltip>

        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
import { mdiAccount } from '@mdi/js'
import SecurityModule from '@/components/util/SecurityModule'

export default {
  name: 'NavigatorComponent',
  data () {
    return {
      userProfileIcon: mdiAccount
    }
  },
  methods: {
    navigate (path) {
      this.$router.replace(path)
    },
    openProfile () {
      this.$store.dispatch('saveRouteToJump', '/profile')
      // TODO this need to moove into 'beforeRouter' action
      const actionFunc = function (vm) { vm.navigate('/profile') }
      SecurityModule.doActionIfUserIsAuthenticated(actionFunc, this)
    }
  }
}
</script>
