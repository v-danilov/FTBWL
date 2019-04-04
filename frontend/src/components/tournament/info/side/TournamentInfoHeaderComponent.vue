<template>
    <v-card>
        <v-card-title>
            <v-layout row justify-center wrap>
                <v-flex xs12 sm12 md12>
                    <h1 :style="{color: this.$vuetify.theme.primary}">{{selectedTournament.name}}</h1>
                </v-flex>
                <v-flex xs12 sm12 md12>
                    <h3 :style="{color: this.$vuetify.theme.primary}">{{selectedTournament.format}}</h3>
                </v-flex>
            </v-layout>
        </v-card-title>
        <v-card-text>
            <v-layout row>
                <v-flex xs6 sm12 md3 text-xs-left offset-xs1>
                    <v-layout column>
                        <v-flex>
                            <span>Место проведения</span>
                        </v-flex>
                        <v-flex>
                            <span>Даты проведения</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs6 sm12 md9 text-xs-left>
                    <v-layout column>
                        <v-flex>
                            {{this.selectedTournament.place}}
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.date}}
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-card-text>
    </v-card>
</template>
<script>
export default {
  name: 'TournamentInfoHeaderComponent',
  props: {
    selectedTournament: {}
  }
}
</script>
