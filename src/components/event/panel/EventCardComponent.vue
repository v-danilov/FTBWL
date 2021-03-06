<template>
  <v-hover>
    <v-card
      slot-scope="{ hover }"
      :class="`elevation-${hover ? 12 : 2}`"
      class="mt-3 mx-auto"
    >
      <v-card-title primary-title>
        <span class="mr-2">
          {{ event.name }}
        </span>
        <v-spacer />
        <span :style="{color : themeColors.secondary}">{{ event.price }} ₽</span>
      </v-card-title>
      <v-card-text @click.stop="openEvent(event.id)">
        <v-row no-gutters>
          <v-col>
            <span>Где</span>
          </v-col>
          <v-col>
            <span>Когда</span>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            {{ event.place.city.name }}, {{ event.place.street }} {{ event.place.building }}
            <a
              :style="{color: themeColors.secondary}"
              href="#"
            >
              {{ event.place.name }}
            </a>
          </v-col>
          <v-col>
            <template v-if="!! event.timeStart">
              {{ event.timeStart }} -
            </template>
            {{ event.timeEnd }}
          </v-col>
        </v-row>
        <v-row class="ma-2">
          <v-divider />
        </v-row>
        <v-row no-gutters>
          <v-col>
            <span>Формат</span>
          </v-col>
          <v-col>
            <span>Организатор</span>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>{{ event.format.value }}</v-col>
          <v-col>{{ event.organizer.lastName }} {{ event.organizer.nickName }} {{ event.organizer.firstName }}</v-col>
        </v-row>
      </v-card-text>
      <v-card-actions v-if="withActions" class="pt-0">
        <v-icon
          :style="{color : statusStyle.color}"
          class="mr-1"
        >
          {{ statusStyle.icon }}
        </v-icon>
        <span :style="{color : statusStyle.color}">
          {{ eventStatusText }}
        </span>
        <v-spacer />
        <v-btn
          @click="emitOpenDialogEvent"
          icon
        >
          <v-icon
            color="primary"
            large
          >assignment_turned_in</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-hover>
</template>
<script>
import statusStyleByCode from '../../util/statusStyleByCode'
import { END_POINTS } from '../../util/constants/EndPointsConstants'
import ConfirmationDialogComponent from '../../dialogs/ConfirmationDialogComponent'
import { ACTIONS } from '../../util/constants/ActionConstants'

export default {
  name: 'EventCardComponent',
  components: { ConfirmationDialogComponent },
  props: {
    event: Object,
    required: true,
    withActions: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      themeColors: this.$vuetify.theme.currentTheme
    }
  },
  computed: {
    eventStatusText () {
      return this.event.status.value
    },
    statusStyle () {
      return statusStyleByCode(this.event.status.code)
    }
  },
  methods: {
    openEvent (eventID) {
      this.$store.dispatch(ACTIONS.COMMONS.CURRENT_ACTIVE_EVENT_ID, eventID)
      this.$router.push(END_POINTS.EVENTS.DEFAULT + eventID)
    },
    emitOpenDialogEvent () {
      this.$emit('open-dialog', { id: this.event.id })
    },
    closeEvent () {
      console.log('Турнир закрыт. Пакеда.')
    }
  }
}
</script>
<style scoped>
</style>
