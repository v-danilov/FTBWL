<template>
  <div>
    <v-row justify="center">
      <v-col cols="1" class="text-center">
        <v-btn fab dark color="secondary">
          <v-icon color="white">
            refresh
          </v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <v-list>
          <draggable v-model="playersLeft" group="playersPairing"
                     @start="drag = true" @end="drag = false"
                     v-bind="dragOptions">
            <transition-group type="transition" :name="!drag ? 'flip-list' : null">
            <v-list-item
              v-for="(element) in playersLeft"
              :key="element.id"
            >
              <v-list-item-avatar>
                <v-img :src="element.avatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-card>
                  <v-card-text tex>
                    {{element.name}}
                  </v-card-text>
                </v-card>
              </v-list-item-content>
            </v-list-item>
            </transition-group>
          </draggable>
        </v-list>
      </v-col>
      <v-col cols="2">
        <v-list disabled>
          <v-list-item
            v-for="i in dataFromServer.tablesNumber"
            :key="i"
          >
            <v-list-item-content>
              <v-card>
                <v-card-text>
                  <span>Стол №{{i}}</span>
                </v-card-text>
              </v-card>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="4">
        <v-list>
          <draggable v-model="playersRight" group="playersPairing"
                     @start="drag = true" @end="drag = false"
                     v-bind="dragOptions">
            <transition-group type="transition" :name="!drag ? 'flip-list' : null">
              <v-list-item color="primary"
                           v-for="(element) in playersRight"
                           :key="element.id"
              >
                <v-list-item-avatar>
                  <v-img :src="element.avatar"></v-img>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-card>
                    <v-card-text>
                      {{element.name}}
                    </v-card-text>
                  </v-card>
                </v-list-item-content>
              </v-list-item>
            </transition-group>
          </draggable>
        </v-list>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import draggable from 'vuedraggable'

export default {
  name: 'PairingMainComponent',
  components: {
    draggable
  },
  data () {
    return {
      drag: false,
      playersLeft: [],
      playersRight: [],
      dataFromServer: {
        tablesNumber: 0,
        players: []
      }
    }
  },
  created () {
    const vueInstance = this

    function splitToLeftAndRight (item, index) {
      let player = {
        id: index,
        name: item.name.first.replace(/^\w/, c => c.toUpperCase()) + ' ' + item.name.last.replace(/^\w/, c => c.toUpperCase()),
        avatar: item.picture.medium
      }
      if (index % 2 === 0) {
        vueInstance.playersLeft.push(player)
      } else {
        vueInstance.playersRight.push(player)
      }
    }

    // TODO get tournament data for pairing creation
    this.$http.get('https://randomuser.me/api/?results=40').then(response => {
      response.data.results.forEach(splitToLeftAndRight)
      this.dataFromServer.tablesNumber = 20
    })
  },
  computed: {
    dragOptions () {
      return {
        animation: 200,
        group: 'description',
        disabled: false,
        ghostClass: 'ghost'
      }
    },
    isArraysBalanced () {
      return this.playersLeft.length !== this.playersRight.length
    }
  }
}
</script>

<style scoped>
</style>
