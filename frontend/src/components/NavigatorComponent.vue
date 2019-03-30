<template>
  <v-navigation-drawer class="primary"
                       permanent
                       mini-variant
                       :mini-variant-width="100"
                       app>
    <v-layout column justify-center fill-height>
    <v-list
      v-for="(element, index) in navigationElements"
      :key="index" class="mt-5">
      <v-list-tile-action>
        <v-btn large flat icon color="white">
          <v-icon large>{{element.icon}}</v-icon>
        </v-btn>
      </v-list-tile-action>
    </v-list>
    </v-layout>
    <!--<v-layout align-center justify-center column fill-height>
      <template v-for="element in navigationElements">
        <v-btn large flat icon color="white" :key="element.name" >
          <v-icon large>{{element.icon}}</v-icon>
        </v-btn>
      </template>
      <v-spacer></v-spacer>
      <v-btn large flat icon color="white">
        <v-icon large>help</v-icon>
      </v-btn>
    </v-layout>-->
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
  }
}
</script>
