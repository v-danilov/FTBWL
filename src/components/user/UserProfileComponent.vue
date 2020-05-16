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
        <h3>Все турниры</h3>
        <v-expansion-panels>
          <v-expansion-panel>
            <v-expansion-panel-header>Турнир в подвале с крысами</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-row>
                <v-col>Дата</v-col>
                <v-col>20-02-2012</v-col>
                <v-col>Формат турнира</v-col>
                <v-col>50 SS</v-col>
                <v-col>Фракция</v-col>
                <v-col>Пики точёные</v-col>
                <v-col>Место в турнире</v-col>
                <v-col>3</v-col>
              </v-row>
              <v-row>
                <v-col>
                  Раунды:
                 <v-col>
                    <v-row dense>
                      <v-col>Раунд 1</v-col>
                      <v-col> Победа </v-col>
                      <v-col> VP: 3 </v-col>
                    </v-row>
                  </v-col>
                  <v-col>
                   <v-row dense>
                      <v-col>Раунд 2</v-col>
                      <v-col>Поражение</v-col>
                      <v-col>VP: 1</v-col>
                    </v-row>
                  </v-col>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
          <v-expansion-panel>
            <v-expansion-panel-header>Турнир в подвале с крысами</v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-row>
                <v-col>Дата</v-col>
                <v-col>20-02-2012</v-col>
                <v-col>Формат турнира</v-col>
                <v-col>50 SS</v-col>
                <v-col>Фракция</v-col>
                <v-col>Пики точёные</v-col>
                <v-col>Место в турнире</v-col>
                <v-col>3</v-col>
              </v-row>
              <v-row>
                <v-col>
                  Раунды:
                 <v-col>
                    <v-row dense>
                      <v-col>Раунд 1</v-col>
                      <v-col> Победа </v-col>
                      <v-col> VP: 3 </v-col>
                    </v-row>
                  </v-col>
                  <v-col>
                   <v-row dense>
                      <v-col>Раунд 2</v-col>
                      <v-col>Поражение</v-col>
                      <v-col>VP: 1</v-col>
                    </v-row>
                  </v-col>
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
