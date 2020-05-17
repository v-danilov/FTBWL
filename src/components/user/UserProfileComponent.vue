<template>
  <div>
    <v-row justify="center">
      <v-col class="text-center">
        <h1>Профиль пользователя {{userProfile.nickname}}</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <h3>Предстоящий турнир</h3>
        <EventCard :event="dummyEvent" :with-actions="false" />
      </v-col>
      <v-col>
        <h3>Текущий турнир</h3>
        <EventCard :event="dummyEvent" :with-actions="false" />
      </v-col>
      <v-col>
        <h3>Завершённый турнир</h3>
        <EventCard :event="dummyEvent" :with-actions="false" />
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <span class="headline">Все турниры (4)</span>
        <v-expansion-panels>
          <v-expansion-panel>
            <v-expansion-panel-header> Турнир не на жизнь, а на смерть 🥈</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-row>
                <v-col cols="4">
                <v-card>
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="overline mb-4">11.07.2021</div>
                      <v-list-item-title class="headline mb-1">Место 2 🥈</v-list-item-title>
                      <v-list-item-subtitle>50 SS</v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-avatar>
                      <v-avatar>
                    <img src="@/assets/fractions/the-guild.png" alt />
                  </v-avatar>
                    </v-list-item-avatar>
                  </v-list-item>
                </v-card>
                </v-col>
                <v-col cols="2">
                      <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 1</v-list-item-title>
                          <v-list-item-subtitle>TP: 11, VP: 6</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 2</v-list-item-title>
                          <v-list-item-subtitle>TP: 13, VP: 2</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 3</v-list-item-title>
                          <v-list-item-subtitle>TP: 15, VP: 7</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#9996;</div>
                            </template>
                            Победа
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                 <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 4</v-list-item-title>
                          <v-list-item-subtitle>TP: 21, VP: 6</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#9996;</div>
                            </template>
                            Победа
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
          <v-expansion-panel>
            <v-expansion-panel-header>Турнир в подвале с крысами 🥇</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-row>
                <v-col cols="4">
                <v-card>
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="overline mb-4">20.02.2012</div>
                      <v-list-item-title class="headline mb-1">Место 1 🥇</v-list-item-title>
                      <v-list-item-subtitle>50 SS</v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-avatar>
                      <v-avatar>
                    <img src="@/assets/fractions/arcanists.png" alt />
                  </v-avatar>
                    </v-list-item-avatar>
                  </v-list-item>
                </v-card>
                </v-col>
                <v-col cols="2">
                      <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 1</v-list-item-title>
                          <v-list-item-subtitle>TP: 7, VP: 3</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 2</v-list-item-title>
                          <v-list-item-subtitle>TP: 12, VP: 11</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#9996;</div>
                            </template>
                            Победа
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 3</v-list-item-title>
                          <v-list-item-subtitle>TP: 12, VP: 11</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#9996;</div>
                            </template>
                            Победа
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
           <v-expansion-panel>
            <v-expansion-panel-header>Турнир на кухне 🥉</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-row>
                <v-col cols="4">
                <v-card>
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="overline mb-4">20.02.2012</div>
                      <v-list-item-title class="headline mb-1">Место 3 🥉</v-list-item-title>
                      <v-list-item-subtitle>50 SS</v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-avatar>
                      <v-avatar>
                    <img src="@/assets/fractions/ten-thunders.png" alt />
                  </v-avatar>
                    </v-list-item-avatar>
                  </v-list-item>
                </v-card>
                </v-col>
                <v-col cols="2">
                      <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 1</v-list-item-title>
                          <v-list-item-subtitle>TP: 7, VP: 3</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 2</v-list-item-title>
                          <v-list-item-subtitle>TP: 9, VP: 3</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 3</v-list-item-title>
                          <v-list-item-subtitle>TP: 12, VP: 4</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#9996;</div>
                            </template>
                            Победа
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
           <v-expansion-panel>
            <v-expansion-panel-header>Турнир в болоте с Мурлоками</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-row>
                <v-col cols="4">
                <v-card>
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="overline mb-4">01.01.2001</div>
                      <v-list-item-title class="headline mb-1">Место 12</v-list-item-title>
                      <v-list-item-subtitle>50 SS</v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-avatar>
                      <v-avatar>
                    <img src="@/assets/fractions/neverborn.png" alt />
                  </v-avatar>
                    </v-list-item-avatar>
                  </v-list-item>
                </v-card>
                </v-col>
                <v-col cols="2">
                      <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 1</v-list-item-title>
                          <v-list-item-subtitle>TP: 0, VP: 1</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 3</v-list-item-title>
                          <v-list-item-subtitle>TP: 2, VP: 2</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
                <v-col cols="2">
                  <v-card>
                    <v-list>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title class="headline">Раунд 3</v-list-item-title>
                          <v-list-item-subtitle>TP: 3, VP: 1</v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-icon>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on }">
                              <div v-on="on">&#128078;</div>
                            </template>
                            Поражение
                          </v-tooltip>
                        </v-list-item-icon>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// import axios from 'axios'
import EventCardComponent from '@/components/event/panel/EventCardComponent'

export default {
  name: 'UserProfileComponent',
  components: {
    EventCard: EventCardComponent
  },
  // beforeRouteEnter (to, from, next) {
  //   next(vm => {
  //     axios.get('/user/profile').then(response => {
  //       console.log('api call')
  //       vm.setUserProfile(response.data)
  //     })
  //   })
  // },
  data () {
    return {
      userProfile: {
        avatar: 'https://randomuser.me/api/portraits/men/88.jpg',
        nickname: 'Player #1'
      },
      dummyEvent: {
        name: 'Имя ивента',
        price: '50',
        place: {
          city: {
            name: 'Moscow'
          },
          street: 'Street name',
          building: '221B'
        },
        timeStart: '22-02-2020',
        timeEnd: '22-02-2021',
        format: {
          value: '50SS'
        },
        organizer: {
          lastName: 'Cool',
          nickname: '',
          firstName: 'guy'
        },
        status: {
          value: 'Registration',
          code: 'REG_ON'
        }
      }
    }
  },
  methods: {
    setUserProfile (up) {
      this.userProfile = up
    }
  }
}
</script>

<style scoped>
</style>
