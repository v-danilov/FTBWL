<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-dialog v-model="dialog" persistent max-width="500px">
    <v-card>
      <v-card-title>
        <span class="headline">Регистрация на турнир</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
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
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="accent" text @click="dialog = false">Отмена</v-btn>
        <v-btn color="primary" rounded @click="registerUser">Зарегистрироваться</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

import {END_POINTS} from '../../util/constants/EndPointsConstants'

export default {
  name: 'EventRegDialogComponent',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    eventId: {
      type: String // TODO full object or DTO with name, date and time
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
      let elements = this.$store.getters.cachedFactions
      let fractionsData = []
      elements.forEach(el => fractionsData.push({id: el.id, name: el.name, imgPath: this.generateIconLink(el.name)}))
      return fractionsData
    }
  },
  methods: {
    generateIconLink (name) {
      return require('@/assets/fractions/' + name.toLowerCase()
        .replace(/ /g, '-')
        .replace(/’/g, '') + '.png')
    },
    registerUser () {
      const userRegData = {
        eventId: this.eventId,
        fractionId: this.fractionId
      }

      this.$http.post(END_POINTS.EVENTS.REG_USER, userRegData)
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
