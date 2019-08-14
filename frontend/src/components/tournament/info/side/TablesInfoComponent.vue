<template>
        <v-card class="ma-1">
            <v-card-text>
              <v-row dense align="center" justify="center">
                <v-col cols="2">
                  <h3>Стол №{{table.tableNumber}}</h3>
                </v-col>
              <v-col>
                <v-row dense align="center" class="text-center" justify="center">
                  <v-col>{{table.firstPlayer.name}}</v-col>
                  <v-col cols="2"  :style="{'color': $vuetify.theme.currentTheme.additional}">
                    {{table.firstPlayer.tp}}
                  </v-col>
                  <v-col cols="2"  :style="{'color': $vuetify.theme.currentTheme.secondary}">
                    {{table.firstPlayer.vp}}
                  </v-col>
                </v-row>
                <v-row dense align="center" class="text-center" justify="center">
                  <v-col>
                    <v-divider></v-divider>
                  </v-col>
                  <v-col cols="2" class="text-center"
                         :style="{'color': $vuetify.theme.currentTheme.additional}">
                    TP
                  </v-col>
                  <v-col cols="2"  class="text-center"  :style="{'color': $vuetify.theme.currentTheme.secondary}">
                    VP
                  </v-col>
                </v-row>
                <v-row dense align="center" class="text-center" justify="center">
                  <v-col class="text-center">{{table.secondPlayer.name}}</v-col>
                  <v-col cols="2"  :style="{'color': $vuetify.theme.currentTheme.additional}">
                    {{table.secondPlayer.tp}}
                  </v-col>
                  <v-col cols="2"  :style="{'color': $vuetify.theme.currentTheme.secondary}">
                    {{table.secondPlayer.vp}}
                  </v-col>
                </v-row>
              </v-col>
              </v-row>
            </v-card-text>
        </v-card>
</template>
<script>
export default {
  name: 'TablesInfoComponent',
  props: {
    table: {
      type: Object,
      required: true
    }
  }
}
</script>
