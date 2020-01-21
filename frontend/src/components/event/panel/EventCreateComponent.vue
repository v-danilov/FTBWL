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
          <v-row class="fill-height" align="center" justify="center">
            <v-col cols="6">
              <v-combobox :items="this.$store.getters.cachedGameSystems.items"
                          item-text="value"
                          v-model="selectedSystem"
                          :return-object="true"
                          required
                          label="Выберите систему для проведения турнира">
              </v-combobox>
            </v-col>
          </v-row>
          <v-btn
            :disabled="selectedSystem === null"
            color="primary"
            @click="e1 = 2"
          >
            Продолжить
          </v-btn>

        </v-stepper-content>

        <!-- Информация о турнире-->
        <v-stepper-content step="2">
          <v-row justify="center">
            <!-- Название -->
            <v-col cols="8">
              <v-text-field v-model="eventName" label="Название"></v-text-field>
            </v-col>
          </v-row>
          <v-row justify="center">
            <!-- Формат проведения -->
            <v-col cols="4">
              <v-combobox :items="this.$store.getters.cachedFormats"
                          item-text="value"
                          v-model="selectedFormat"
                          :return-object="true"
                          label="Формат"
                          required>
              </v-combobox>
            </v-col>
            <!-- Схемпул (рулпак) -->
            <v-col cols="4">
              <v-combobox :items="this.$store.getters.cachedRulePacks"
                          v-model="selectedRulePack"
                          item-text="name"
                          :return-object="true"
                          label="Rulepack"
                          required>
              </v-combobox>
            </v-col>
          </v-row>
          <v-row justify="center">
            <!-- Место проведения -->
            <v-col cols="4">
              <v-combobox :items="this.$store.getters.cachedPlaces"
                          v-model="selectedPlace"
                          item-text="name"
                          :return-object="true"
                          append-icon="location_on"
                          label="Площадка"
                          required>
              </v-combobox>
            </v-col>
            <!-- Стоимость -->
            <v-col cols="4">
              <v-text-field append-icon="monetization_on" label="Стоимость" v-model="price"></v-text-field>
            </v-col>
          </v-row>
          <v-row justify="center">
            <!-- Организатор -->
            <v-col cols="8">
              <v-text-field disabled value="Параллельный 'Мерзкий' Лион" label="Организатор"></v-text-field>
            </v-col>
          </v-row>
          <v-btn
            color="primary"
            @click="e1 = 3"
          >
            Продолжить
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <!-- Заголовок -->
          <v-row align="center" justify="center" class="text-center">
            <v-col>
              <h3>Настройка раунда</h3>
            </v-col>
            <v-col>
              <h3>Сохранённые раунды</h3>
            </v-col>
          </v-row>
          <!-- Создание раундов -->
          <v-row>
            <v-col>
              <v-row dense align="center">
                <!-- Дата игрового дня -->
                <v-col cols="2">
                  <span>Дата</span>
                </v-col>
                <v-col>
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
                      <v-btn text rounded color="accent" @click="menu = false">Отмена</v-btn>
                      <v-btn text rounded color="secondary" @click="$refs.menu.save(date)">OK</v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>
              <v-row dense align="center">
                <v-col>
                  <span>Время</span>
                </v-col>
                <v-col>
                  <v-text-field v-model="timeStart" v-mask="timeMask" single-line label="Начало"></v-text-field>
                </v-col>
                <v-col>
                  <v-text-field v-model="timeEnd" v-mask="timeMask" single-line label="Конец"></v-text-field>
                </v-col>
                <v-col cols="1">
                  <v-btn icon fab text rounded color="primary"
                         :disabled="saveIsActive" class="mx-1"
                         @click="saveRoundTime">
                    <v-icon>
                      queue
                    </v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
            <v-col>
              <v-row dense
                v-for="(day, index) in this.roundsDates" :key="index">
                <v-col>
                  {{formatDate(day)}}
                  <v-btn small color="accent"
                         icon
                         text
                         @click="deleteDay(index)">
                    <v-icon>delete_outline</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>

          <v-btn
            color="primary"
            @click="createEvent"
            :disabled="roundsDates.length === 0"
          >
            Создать турнир
          </v-btn>

        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-dialog>
</template>
<script>
import {END_POINTS} from '../../util/constants/EndPointsConstants'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
import UserSession from '../../../store/cookie/userSessionClass'
import {mask, tokens} from 'vue-the-mask'

export default {
  directives: {
    mask, tokens
  },
  name: 'EventCreateComponent',
  props: {
    visible: {
      type: Boolean,
      required: true
    }
  },
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
      eventName: '',
      selectedSystem: null,
      selectedFormat: null,
      selectedPlace: null,
      selectedRulePack: null,
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
          this.$emit('close-event-create-dialog')
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
    createEvent () {
      let eventDates = this.calculateEventDaysByRounds()
      let event = {
        system: this.systemsCombobox.selected, // todo or system name
        name: this.eventName,
        format: this.formatsCombobox.selected, // todo or system name
        place: this.placesCombobox.selected,
        rulesPack: this.rulesPacksCombobox.selected, // todo or system name
        price: this.price,
        rounds: this.roundsDates,
        dateStart: eventDates.firstDate,
        dateEnd: eventDates.lastDate,
        organizerId: UserSession.getUser()
      }
      // todo check response
      this.$http.post(END_POINTS.EVENT.CREATE, event).then(response => {
        if (response.status !== HTTPResponseStatusConstants.OK) {
          console.log('NotOK')
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    calculateEventDaysByRounds () {
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
      return sDate.getDate() + '' + '.' + (sDate.getMonth() + 1) + '.' + sDate.getFullYear() +
        ' (' + sDate.getHours() + ':' + sDate.getMinutes() + ' - ' + eDate.getHours() + ':' + eDate.getMinutes() + ')'
    }
  }
}
</script>
