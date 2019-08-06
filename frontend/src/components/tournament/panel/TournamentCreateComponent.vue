<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-dialog
    v-model="dialog"
    width="45%"
  >
    <v-stepper v-model="e1" alt-labels>
      <v-stepper-header>
        <v-stepper-step :complete="e1 > 1" step="1">Система</v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="e1 > 2" step="2">Свойства</v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="e1 > 3" step="3">Раунды</v-stepper-step>

      </v-stepper-header>

      <v-stepper-items>
        <!-- Выбор системы-->
        <v-stepper-content step="1">
          <v-layout align-center justify-center row fill-height>
            <v-flex xs6>
              <v-combobox v-model="systemsCombobox.selected"
                          :items="systemsCombobox.items"
                          item-text="name"
                          :return-object="true"
                          required
                          label="Выберите систему для проведения турнира">
              </v-combobox>
            </v-flex>
          </v-layout>
          <v-btn
            :disabled="systemsCombobox.selected === null"
            color="primary"
            @click="e1 = 2"
          >
            Продолжить
          </v-btn>

        </v-stepper-content>

        <!-- Информация о турнире-->
        <v-stepper-content step="2">
          <v-layout justify-space-around column>
            <!-- Название -->
            <v-flex>
              <v-text-field v-model="tournamentName" label="Название"></v-text-field>
            </v-flex>
            <v-flex>
              <v-layout align-center justify-space-around row>
                <!-- Формат проведения -->
                <v-flex xs4>
                  <v-combobox v-model="formatsCombobox.selected"
                              :items="formatsCombobox.items"
                              item-text="name"
                              :return-object="true"
                              label="Формат"
                              required>
                  </v-combobox>
                </v-flex>
                <!-- Схемпул (рулпак) -->
                <v-flex xs4>
                  <v-combobox v-model="rulesPacksCombobox.selected"
                              :items="rulesPacksCombobox.items"
                              item-text="name"
                              :return-object="true"
                              label="Rulepack"
                              required>
                  </v-combobox>
                </v-flex>
              </v-layout>
            </v-flex>
            <v-flex>
              <v-layout align-center justify-space-around row>
                <!-- Место проведения -->
                <v-flex xs4>
                  <v-combobox v-model="placesCombobox.selected"
                              :items="placesCombobox.items"
                              item-text="name"
                              :return-object="true"
                              append-icon="location_on"
                              label="Площадка"
                              required>
                  </v-combobox>
                </v-flex>
                <!-- Стоимость -->
                <v-flex xs4>
                  <v-text-field append-icon="monetization_on" label="Стоимость" v-model="price"></v-text-field>
                </v-flex>
              </v-layout>
            </v-flex>
            <!-- Организатор -->
            <v-flex>
              <v-text-field disabled value="Параллельный 'Мерзкий' Лион" label="Организатор"></v-text-field>
            </v-flex>
          </v-layout>
          <v-btn
            color="primary"
            @click="e1 = 3"
          >
            Продолжить
          </v-btn>

        </v-stepper-content>

        <v-stepper-content step="3">
          <!-- Заголовок -->
          <v-layout align-center justify-center class="text-xs-center" row>
            <v-flex>
              <h3>Настройка раунда</h3>
            </v-flex>
            <v-flex>
              <h3>Сохранённые раунды</h3>
            </v-flex>
          </v-layout>
          <!-- Данные -->
          <v-layout row class="mt-2">
            <!-- Создание раундов-->
            <v-flex xs6>
              <v-layout column>
                <!-- Дата игрового дня -->
                <v-flex xs6>
                  <v-layout row>
                    <v-flex xs6>
                      <span>Дата раунда</span>
                    </v-flex>
                  </v-layout>
                </v-flex>
                <v-flex xs1>
                  <v-layout row>
                    <v-flex xs9>
                      <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :return-value.sync="date"
                        transition="scale-transition"
                        offset-y
                        lazy
                        full-width
                        min-width="290px"
                      >
                        <template v-slot:activator="{ on }">
                          <v-text-field
                            v-model="date"
                            prepend-icon="event"
                            readonly
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker v-model="date"
                                       no-title
                                       scrollable
                                       color="secondary">
                          <v-spacer></v-spacer>
                          <v-btn text round flat color="accent" @click="menu = false">Отмена</v-btn>
                          <v-btn text round flat color="secondary" @click="$refs.menu.save(date)">OK</v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-flex>
                  </v-layout>
                </v-flex>
                <v-flex xs6>
                  <span>Время раунда</span>
                </v-flex>
                <v-flex>
                  <v-layout justify-space-around>
                    <v-flex xs4>
                      <v-text-field v-model="timeStart" v-mask="timeMask" single-line label="Начало"></v-text-field>
                    </v-flex>
                    <v-flex xs4>
                      <v-text-field  v-model="timeEnd" v-mask="timeMask" single-line  label="Конец"></v-text-field>
                    </v-flex>
                    <v-flex xs2>
                      <v-btn icon fab flat round color="primary"
                             :disabled="saveIsActive" class="mx-1"
                             @click="saveRoundTime">
                        <v-icon>
                          queue
                        </v-icon>
                      </v-btn>
                    </v-flex>
                  </v-layout>
                </v-flex>
              </v-layout>
            </v-flex>
            <!-- Сохранённые раундыы-->
            <v-flex xs6>
              <v-layout column>
                <v-flex>
                  <v-layout align-start fill-height column>
                    <v-flex v-for="(day, index) in this.roundsDates" :key="index">
                      {{formatDate(day)}}
                      <v-btn small color="accent"
                             icon
                             flat
                             @click="deleteDay(index)"
                             max-height="5px"
                             max-widht="5px">
                        <v-icon>delete_outline</v-icon>
                      </v-btn>
                    </v-flex>
                  </v-layout>
                </v-flex>
              </v-layout>
            </v-flex>
          </v-layout>

          <v-btn
            color="primary"
            @click="createTournament"
            :disabled="roundsDates.length === 0"
          >
            Создать турнир
          </v-btn>

        </v-stepper-content>

        <!-- Создание раундов -->
        <v-stepper-content step="4">
          <v-card
            class="mb-12"
            color="grey lighten-1"
            height="200px"
          >
            <v-card-title>
              Создание раундов
            </v-card-title>
            <v-card-text>
              Тут короче раунды по дням
            </v-card-text>
          </v-card>

          <v-btn
            color="primary"
            @click="e1 = 1"
          >
            Продолжить
          </v-btn>

        </v-stepper-content>

      </v-stepper-items>
    </v-stepper>
  </v-dialog>
</template>
<script>
import TournamentInfoBodyComponent from '../info/side/TournamentInfoBodyComponent'
import TournamentInfoParametersComponent from '../info/side/TournamentInfoParametersComponent'
import {END_POINTS} from '../../util/constants/EndPointsConstants'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
import UserSession from '../../../store/cookie/userSessionClass'
import {mask, tokens} from 'vue-the-mask'

export default {
  directives: {
    mask, tokens
  },
  name: 'TournamentCreateComponent',
  props: {
    visible: {
      type: Boolean,
      required: true
    }
  },
  components: {TournamentInfoParametersComponent, TournamentInfoBodyComponent},
  created () {
    tokens.H = {pattern: /[0-2]/}
    tokens.h = {pattern: /[0-9]/}
    tokens.M = {pattern: /[0-5]/}
    tokens.m = {pattern: /[0-9]/}
  },
  data () {
    return {
      e1: 0,
      menu: false,
      tournamentName: '',
      systemsCombobox: {
        items: [
          {id: 1, name: 'Система 1'},
          {id: 2, name: 'Система 2'},
          {id: 3, name: 'Система 3'},
          {id: 4, name: 'Система 4'}
        ],
        selected: null
      },
      formatsCombobox: {
        items: [
          {id: 1, name: '50 SS'},
          {id: 2, name: '30 SS'},
          {id: 3, name: '9000 SS'}
        ],
        selected: null
      },
      placesCombobox: {
        items: [
          {id: 1, name: 'GeekWars'},
          {id: 2, name: 'WarsGeek'},
          {id: 3, name: 'WarsWars'},
          {id: 4, name: 'GeekGeek'}
        ],
        selected: null
      },
      rulesPacksCombobox: {
        items: [
          {id: 1, name: 'GG2018'},
          {id: 2, name: 'GG2019'},
          {id: 3, name: 'WP2020'},
          {id: 4, name: 'GLHF2k50'}
        ],
        selected: null
      },
      price: 0,
      date: new Date().toISOString().substr(0, 10),
      timeMask: 'Hh:Mm', // ^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$
      timeStart: '',
      timeEnd: '',
      roundsDates: []
    }
  },
  computed: {
    dialog: {
      get () {
        return this.visible
      },
      set (value) {
        if (!value) {
          this.$emit('close-tournament-create-dialog')
        }
      }
    },
    saveIsActive () {
      let startSplitted = this.parseTime(this.timeStart)
      let endSplitted = this.parseTime(this.timeEnd)
      return !(Number(startSplitted.hours + startSplitted.minutes) < Number(endSplitted.hours + endSplitted.minutes)) // revert because of 'disabled' property
    }
  },
  methods: {
    saveRoundTime () {
      let startSplitted = this.parseTime(this.timeStart)
      let endSplitted = this.parseTime(this.timeEnd)
      // whats going on? we have kek risk
      // https://stackoverflow.com/questions/5619202/converting-a-string-to-a-date-in-javascript
      let parts = this.date.split('-')
      let startDate = new Date(parts[0], parts[1] - 1, parts[2]).setHours(startSplitted.hours, startSplitted.minutes, 0)
      let endDate = new Date(parts[0], parts[1] - 1, parts[2]).setHours(endSplitted.hours, endSplitted.minutes, 0)
      let roundDate = {startDate: startDate, endDate: endDate}
      if (this.thereIsNoCrossDates(roundDate)) {
        this.roundsDates.push(roundDate)
        this.timeStart = ''
        this.timeEnd = ''
      }
    },
    deleteDay (index) {
      this.roundsDates.splice(index, 1)
    },
    thereIsNoCrossDates (dateToCheck) {
      console.log(this.roundsDates.length)
      for (const val of this.roundsDates) {
        if (!((dateToCheck.startDate < val.startDate &&
            dateToCheck.endDate < val.startDate) ||
            dateToCheck.startDate > val.endDate)) {
          return false
        }
      }
      return true
    },
    createTournament () {
      let tournamentDates = this.calculateTournamentDaysByRounds()
      let tournament = {
        system: this.systemsCombobox.selected, // todo or system name
        name: this.tournamentName,
        format: this.formatsCombobox.selected, // todo or system name
        place: this.placesCombobox.selected,
        rulesPack: this.rulesPacksCombobox.selected, // todo or system name
        price: this.price,
        rounds: this.roundsDates,
        dateStart: tournamentDates.firstDate,
        dateEnd: tournamentDates.lastDate,
        organizerId: UserSession.getUser()
      }
      // todo check response
      this.$http.post(END_POINTS.TOURNAMENT.CREATE, tournament).then(response => {
        if (response.status !== HTTPResponseStatusConstants.OK) {
          console.log('NotOK')
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    calculateTournamentDaysByRounds () {
      let firstDate, lastDate
      firstDate = this.roundsDates[0].startDate
      lastDate = this.roundsDates[0].endDate
      for (const date of this.roundsDates) {
        if (firstDate > date) {
          firstDate = date
        }
        if (lastDate < date) {
          lastDate = date
        }
      }
      return {firstDate: firstDate, lastDate: lastDate}
    },
    parseTime (val) {
      let splitted = val.split(':')
      return {hours: splitted[0], minutes: splitted[1]}
    },
    formatDate (date) {
      let sDate = new Date(date.startDate)
      let eDate = new Date(date.endDate)
      return sDate.getDate() + '' + '.' + sDate.getMonth() + '.' + sDate.getFullYear() +
        ' (' + sDate.getHours() + ':' + sDate.getMinutes() + ' - ' + eDate.getHours() + ':' + eDate.getMinutes() + ')'
    }
  }
}
</script>
