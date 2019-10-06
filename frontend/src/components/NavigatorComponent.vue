<template>
  <v-navigation-drawer class="primary"
                       permanent
                       mini-variant
                       :mini-variant-width="70"
                       app>
    <v-list nav>
      <v-list-item link
                   @click="navigate(element.path)"
                   v-for="(element, index) in navigationElements" :key="index">
        <v-list-item-content>
          <v-icon large color="white">{{element.icon}}</v-icon>
        </v-list-item-content>
      </v-list-item>
    </v-list>
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
  methods: {
    navigate (path) {
      this.$router.replace(path)
    }
  }
}
</script>
