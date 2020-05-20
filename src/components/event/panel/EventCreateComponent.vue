<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-dialog v-model="dialog" width="45%">
    <v-stepper v-model="e1" alt-labels>
      <v-stepper-header>
        <v-stepper-step :complete="e1 > 1" step="1">Система</v-stepper-step>

        <v-divider />

        <v-stepper-step :complete="e1 > 2" step="2">Свойства</v-stepper-step>

        <v-divider />

        <v-stepper-step :complete="e1 > 3" step="3">Раунды</v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <!-- Выбор системы-->
        <v-stepper-content step="1">
          <v-row class="fill-height" align="center" justify="center">
            <v-col cols="6">
              <v-combobox
                :items="gamesSystemsFromCache"
                v-model="selectedSystem"
                :return-object="true"
                item-text="value"
                required
                label="Выберите систему для проведения турнира"
              />
            </v-col>
          </v-row>
          <v-btn
            :disabled="selectedSystem === null"
            @click="e1 = 2"
            color="primary"
          >
            Продолжить
          </v-btn>
        </v-stepper-content>

        <!-- Информация о турнире-->
        <v-stepper-content step="2">
          <v-row justify="center">
            <!-- Название -->
            <v-col cols="8">
              <v-text-field v-model="eventName" label="Название" />
            </v-col>
          </v-row>
          <v-row justify="center">
            <!-- Формат проведения -->
            <v-col cols="4">
              <v-combobox
                :items="formatsFromCache"
                v-model="selectedFormat"
                :return-object="true"
                item-text="value"
                label="Формат"
                required
              />
            </v-col>
            <!-- Схемпул (рулпак) -->
            <v-col cols="4">
              <v-combobox
                :items="rulePacksFromCache"
                v-model="selectedRulePack"
                :return-object="true"
                item-text="value"
                label="Rulepack"
                required
              />
            </v-col>
          </v-row>
          <v-row justify="center">
            <!-- Место проведения -->
            <v-col cols="4">
              <v-combobox
                :items="placesFromCache"
                v-model="selectedPlace"
                :return-object="true"
                item-text="name"
                append-icon="location_on"
                label="Площадка"
                required
              />
            </v-col>
            <!-- Стоимость -->
            <v-col cols="4">
              <v-text-field
                v-model="price"
                append-icon="monetization_on"
                label="Стоимость"
              />
            </v-col>
          </v-row>
          <v-row justify="center">
            <!-- Организатор -->
            <v-col cols="8">
              <v-text-field
                disabled
                :value="organizerName"
                label="Организатор"
              />
            </v-col>
          </v-row>
          <v-btn @click="e1 = 3" color="primary">
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
              <v-row align="center">
                <!-- Дата игрового дня -->
                <v-col cols="2">
                  <span>Дата</span>
                </v-col>
                <v-col cols="8">
                  <v-menu
                    v-model="menu"
                    :close-on-content-click="false"
                    transition="scale-transition"
                    offset-y
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        v-model="dateFormatted"
                        v-on="on"
                        append-icon="event"
                        readonly
                      />
                    </template>
                    <v-date-picker
                      v-model="date"
                      no-title
                      @input="menu = false"
                      color="secondary"
                    >
                    </v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>
              <v-row align="center">
                <v-col cols="2">
                  <span>Время</span>
                </v-col>
                <v-col cols="3">
                  <v-text-field
                    v-model="roundTimeStart"
                    v-mask="timeMask"
                    single-line
                    label="12:11"
                  />
                </v-col>
                <v-col cols="3">
                  <v-text-field
                    v-model="roundTimeEnd"
                    v-mask="timeMask"
                    single-line
                    label="13:10"
                  />
                </v-col>
                <v-col cols="2">
                  <v-btn
                    :disabled="!timeIsConsistent"
                    @click="saveRoundTime"
                    rounded
                    icon
                    color="primary"
                  >
                    <v-icon>
                      queue
                    </v-icon>
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
            <v-col>
              <v-list two-line>
                <v-list-item
                  v-for="(rounDate, index) in this.roundsDates"
                  :key="index"
                >
                  <v-list-item-content>
                    <v-list-item-title
                      v-text="roundTimeBoundToString(rounDate.start, rounDate.end)"
                    ></v-list-item-title>
                    <v-list-item-subtitle
                      v-text="rounDate.end.toDateString()"
                    ></v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-btn icon text color="accent" @click="deleteRound(index)">
                      <v-icon>delete_outline</v-icon>
                    </v-btn>
                  </v-list-item-action>
                </v-list-item>
              </v-list>
            </v-col>
          </v-row>

          <v-btn
            @click="createEvent"
            :disabled="roundsDates.length === 0"
            color="primary"
          >
            Создать турнир
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-dialog>
</template>
<script>
import { END_POINTS } from '../../util/constants/EndPointsConstants'
import { mask, tokens } from 'vue-the-mask'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'
import UserCookiesClass from '../../../store/cookie/UserCookiesClass'

export default {
  name: 'EventCreateComponent',
  directives: {
    mask,
    tokens
  },
  props: {
    visible: {
      type: Boolean,
      required: true
    }
  },
  created () {
    tokens.H = { pattern: /[0-2]/ }
    tokens.h = { pattern: /[0-9]/ }
    tokens.M = { pattern: /[0-5]/ }
    tokens.m = { pattern: /[0-9]/ }
  },
  data: vm => ({
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
    roundTimeStart: '',
    roundTimeEnd: '',
    roundsDates: [],
    timeIsConsistent: false
  }),
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
    gamesSystemsFromCache () {
      return Array.from(this.$store.getters.cachedGameSystems.values())
    },
    formatsFromCache () {
      return Array.from(this.$store.getters.cachedFormats.values())
    },
    rulePacksFromCache () {
      return Array.from(this.$store.getters.cachedRulePacks.values())
    },
    placesFromCache () {
      return this.$store.getters.cachedPlaces
    },
    organizerName () {
      let userOrganizer = UserCookiesClass.getAutheticatedUser()
      return (
        userOrganizer.firstName +
        " '" +
        userOrganizer.nickname +
        "' " +
        userOrganizer.lastName
      )
    },
    dateFormatted () {
      return this.formatDate(this.date)
    },
    roundStartDate () {
      if (this.roundTimeStart) {
        return this.setTimeToSelctedDate(this.roundTimeStart)
      }
      return null
    },
    roundEndDate () {
      if (this.roundTimeEnd) {
        return this.setTimeToSelctedDate(this.roundTimeEnd)
      }
      return null
    }
  },
  watch: {
    // using roundStartDate & roundEndDate because computed cache not reactive Date object
    roundStartDate (newValue, oldValue) {
      this.checkRoundDates(newValue, this.roundEndDate)
    },
    roundEndDate (newValue, oldValue) {
      this.checkRoundDates(this.roundStartDate, newValue)
    }
  },
  methods: {
    formatDate (date) {
      if (!date) return null

      const [year, month, day] = date.split('-')
      return `${day}.${month}.${year}`
    },
    saveRoundTime () {
      // whats going on? we have kek risk
      // https://stackoverflow.com/questions/5619202/converting-a-string-to-a-date-in-javascript
      const roundDates = { start: this.roundStartDate, end: this.roundEndDate }
      if (this.thereIsNoCrossDates(roundDates)) {
        roundDates.start = new Date(roundDates.start)
        roundDates.end = new Date(roundDates.end)
        this.roundsDates.push(roundDates)
        this.roundTimeStart = ''
        this.roundTimeEnd = ''
      }
    },
    deleteRound (index) {
      this.roundsDates.splice(index, 1)
    },
    thereIsNoCrossDates (dateToCheck) {
      for (const val of this.roundsDates) {
        if (
          !(
            (dateToCheck.start < val.end && dateToCheck.end < val.start) ||
            dateToCheck.start > val.end
          )
        ) {
          return false
        }
      }
      return true
    },
    createEvent () {
      const event = [
        {
          name: this.eventName,
          place: { id: this.selectedPlace.id },
          gameSystem: { code: this.selectedSystem.code },
          format: { code: this.selectedFormat.code },
          rulepack: { code: this.selectedRulePack.code },
          price: this.price,
          rounds: this.roundsDates
        }
      ]
      console.log(event)
      this.$http
        .post(END_POINTS.EVENTS.DEFAULT, event)
        .then(response => {
          this.$store.dispatch('notifications/add', {
            type: NOTIFICATION_TYPES.SUCCESS,
            text: 'Event created <link>'
          })
        })
        .catch(err => {
          this.$store.dispatch('notifications/add', {
            type: NOTIFICATION_TYPES.ERROR,
            text: 'Event creation error. Try again later'
          })
          console.log(err)
        })
    },
    calculateEventDaysByRounds () {
      let firstDate, lastDate
      firstDate = this.roundsDates[0].timeStart
      lastDate = this.roundsDates[0].timeEnd
      for (const date of this.roundsDates) {
        if (firstDate > date) {
          firstDate = date
        }
        if (lastDate < date) {
          lastDate = date
        }
      }
      return { firstDate: firstDate, lastDate: lastDate }
    },
    setTimeToSelctedDate (time) {
      const splitted = time.split(':')
      let dateWithTime = new Date(this.date)
      dateWithTime.setHours(splitted[0])
      dateWithTime.setMinutes(splitted[1])
      return dateWithTime
    },
    checkRoundDates (startDate, endDate) {
      if (!this.dateIsValid(startDate) || !this.dateIsValid(endDate)) {
        this.timeIsConsistent = false
      }
      this.timeIsConsistent = startDate < endDate
    },
    dateIsValid (d) {
      return d instanceof Date && !isNaN(d)
    },
    roundTimeBoundToString (startDate, endDate) {
      const prependZero = number => {
        return number > 9 ? '' + number : '0' + number
      }
      const start = startDate.getHours() + ':' + prependZero(startDate.getMinutes())
      const end = endDate.getHours() + ':' + prependZero(endDate.getMinutes())
      return start + ' - ' + end
    }
  }
}
</script>

<style scoped></style>
