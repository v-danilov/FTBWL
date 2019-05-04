<template>
    <v-card class="mt-2">
        <v-card-text>
            <v-layout row>
                <!-- LEFT BLOCK -->
                <!-- Labels -->
                <v-flex xs3 sm3 md3 text-xs-left offset-xs1>
                    <v-layout column>
                        <v-flex>
                            <span class="text-highlight-primary">Формат проведения</span>
                        </v-flex>
                        <v-flex>
                            <span class="text-highlight-primary">Количество туров</span>
                        </v-flex>
                        <v-flex>
                            <span class="text-highlight-primary">Схемпул</span>
                        </v-flex>
                        <v-flex>
                            <span class="text-highlight-primary">Требования к покрасу</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <!-- Data -->
                <v-flex xs3 sm3 md3 text-xs-left>
                    <v-layout column>
                        <v-flex>
                            {{ this.selectedTournament.format }}
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.roundsNumber || 'Не указано' }}
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.schemePool || 'Не указан'}}
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.colorRequirements || 'Не предъявляются'}}
                        </v-flex>
                    </v-layout>
                </v-flex>

                <!-- RIGHT BLOCK -->
                <!-- Labels-->
                <v-flex xs3 sm3 md3 text-xs-left>
                    <v-layout column>
                        <v-flex>
                            <span class="text-highlight-primary">Статус туринра</span>
                        </v-flex>
                        <v-flex>
                            <span class="text-highlight-primary">Стоимость участия</span>
                        </v-flex>
                        <v-flex>
                            <span class="text-highlight-primary">Участников</span>
                        </v-flex>
                        <v-flex>
                            <span class="text-highlight-primary">Организатор</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <!-- Data -->
                <v-flex xs3 sm3 md3 text-xs-left>
                    <v-layout column>
                        <v-flex>
                            {{this.selectedTournament.status}}
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.price || 0}} ₽
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.participantsNumber || 'Не известно'}}
                        </v-flex>
                        <v-flex>
                            {{this.selectedTournament.organizer}}
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-card-text>
    </v-card>
</template>
<script>
export default {
  name: 'TournamentInfoParametersComponent',
  props: {
    selectedTournament: {}
  }
}
</script>
