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
import UserCookiesClass from '@/store/cookie/UserCookiesClass'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

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
      const userData = UserCookiesClass.getAutheticatedUser()
      console.log(userData)
      console.log(userData == null)
      if (userData == null) {
        this.$store.dispatch('saveRouteToJump', '/profile')
        this.$store.dispatch('notifications/add', {
          type: NOTIFICATION_TYPES.INFO,
          text: 'Please, login to create an event'
        })
        this.$router.push('/login')
      } else {
        this.navigate('/profile')
      }
    }
  }
}
</script>
