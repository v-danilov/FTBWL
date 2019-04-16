<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-dialog v-model="dialog" persistent max-width="500px">
    <v-card>
      <v-card-title>
        <span class="headline">Регистрация на турнир</span>
      </v-card-title>
      <v-card-text>
        <v-container grid-list-md>
          <v-layout wrap>
            <v-flex xs12>
              <v-select label="Выберите фракцию"
                        v-model="selectedFraction"
                        :items="storedElements"
                        return-object
                        item-text="name">
                <template v-slot:item = "{ item, index }">
                  <v-avatar>
                    <img :src="item.imgPath" alt="">
                  </v-avatar>
                  {{item.name}}
                </template>
                <template v-slot:selection = "{ item, index }">
                  <div>
                  <v-avatar>
                    <img :src="item.imgPath" alt="">
                  </v-avatar>
                  {{item.name}}
                  </div>
                </template>
              </v-select>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="accent" flat @click="dialog = false">Отмена</v-btn>
        <v-btn color="primary" round @click="registerUser">Зарегистрироваться</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

import {END_POINTS} from '../../util/constants/EndPointsConstants'

export default {
  name: 'TournamentRegDialogComponent',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    tournamentId: {
      type: Number
    }
  },
  data () {
    return {
      selectedFraction: {}
    }
  },
  computed: {
    dialog: {
      get () {
        return this.visible
      },
      set (value) {
        if (!value) {
          this.$emit('close-reg-dialog')
        }
      }
    },
    storedElements () {
      let elements = this.$store.getters.cachedFractions
      let fractionsData = []
      elements.forEach(el => fractionsData.push({id: el.id, name: el.name, imgPath: this.generateIconLink(el.name)}))
      return fractionsData
    }
  },
  methods: {
    generateIconLink (name) {
      return require('@/assets/fractions/' + name.toLowerCase().replace(' ', '-') + '.png')
    },
    registerUser () {
      const userRegData = {
        tournamentId: this.tournamentId,
        fractionId: this.fractionId,
        // TODO complete user id to backend request
        userId: 0}

      this.$http.post(END_POINTS.TOURNAMENT.REG_USER, userRegData)
        .then(response => {
          console.log(response)
        })
        .catch(reason => {
          console.log(reason)
        })
    }
  }

}
</script>

<style scoped>

</style>
