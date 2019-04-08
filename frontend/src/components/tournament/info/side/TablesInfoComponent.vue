<template>
    <v-layout column>
        <v-card class="ma-1" v-for="table in tables" :key="table.tableNumber">
            <v-card-text>
                <v-layout row>
                    <v-flex xs2><h3>Стол</h3></v-flex>
                    <v-flex xs8>{{table.firstPlayer.name}}</v-flex>
                    <v-flex xs1>{{table.firstPlayer.tp}}</v-flex>
                    <v-flex xs1>{{table.firstPlayer.vp}}</v-flex>
                </v-layout>
                <v-layout row>
                    <v-flex xs2></v-flex>
                    <v-flex xs8 class="pt-2">
                        <v-divider :color="vuetifyTheme.additional"></v-divider>
                    </v-flex>
                    <v-flex xs1>TP</v-flex>
                    <v-flex xs1>VP</v-flex>
                </v-layout>
                <v-layout row>
                    <v-flex xs2><h3>№{{table.tableNumber}}</h3></v-flex>
                    <v-flex xs8>{{table.secondPlayer.name}}</v-flex>
                    <v-flex xs1>{{table.secondPlayer.tp}}</v-flex>
                    <v-flex xs1>{{table.secondPlayer.vp}}</v-flex>
                </v-layout>
            </v-card-text>
        </v-card>
    </v-layout>
</template>
<script>
export default {
  name: 'TablesInfoComponent',
  props: {
    tables: Array,
    vuetifyTheme: {}
  }
}
</script>
