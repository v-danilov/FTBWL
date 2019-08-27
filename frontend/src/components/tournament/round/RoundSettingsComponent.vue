<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card>
      <!-- Header (toolbar) -->
      <v-app-bar app dark clipped-left color="primary">
        <v-toolbar-title>Раунд {{roundNumber + 1}}</v-toolbar-title>
        <div class="flex-grow-1"></div>
        <v-btn icon fab color="accent"
        @click="dialog = !dialog">
          <v-icon color="white">
            close
          </v-icon>
        </v-btn>
        <v-btn icon color="primary" fab>
          <v-icon color="white">
            save
          </v-icon>
        </v-btn>
      </v-app-bar>
      <!-- Sidebar (navbar) -->
      <v-navigation-drawer
        class="compact-form"
        app
        v-model="drawer"
        width="200px"
        permanent
        clipped>
        <v-row no-gutters>
          <v-col class="mt-3 ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 1"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              class="styled-input"
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 2"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 3"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 4"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Схема 5"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Стратегия"
            ></v-select>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="ml-3 mr-3">
            <v-select
              :items="selectorData"
              item-text="name"
              outlined
              label="Расстановка"
            ></v-select>
          </v-col>
        </v-row>
      </v-navigation-drawer>
      <v-content id="body-container"
                 class="pl-200 overflow-y-hidden">
        <v-container fluid >
          <PairingMainComponent></PairingMainComponent>
        </v-container>
      </v-content>
    </v-card>
  </v-dialog>
</template>

<script>
import PairingMainComponent from '../pairing/PairingMainComponent'
export default {
  name: 'RoundSettingsComponent',
  components: {PairingMainComponent},
  props: {
    roundNumber: {
      required: true,
      type: Number
    },
    roundData: {}
  },
  data () {
    return {
      selectorData: [
        {id: 0, name: 'Element 1'},
        {id: 1, name: 'Element 2'},
        {id: 2, name: 'Element 3'},
        {id: 3, name: 'Element 4'},
        {id: 4, name: 'Element 5'}
      ],
      dialog: true,
      notifications: false,
      sound: true,
      widgets: false
    }
  },
  computed: {
    clientScreenSize () {
      return window.innerHeight ||
        document.documentElement.clientHeight ||
        document.body.clientHeight
    }
  }
}
</script>

<style scoped>
  #styled-input {
    height: 40px;
    font-size: 20pt;
  }
  .styled-input label[for] {
    height: 40px;
    font-size: 20pt;
  }
</style>
