<template>
  <v-row align="stretch">
    <v-col>
      <v-row>
        <!--Filter -->
        <v-col :cols="filterWidth">
          <div transition="scroll-y-transition">
            <v-btn
              v-if="filter.isHidden"
              @click="filter.isHidden = !filter.isHidden"
              rounded
              color="primary"
            >
              <v-icon large>search</v-icon>
              Открыть фильтр
            </v-btn>
            <EventFilter
              v-else
              @close-event-filter="filter.isHidden = true"
            />
          </div>
        </v-col>
        <v-col cols="3">
          <v-btn
            @click="openEventCreationDialog()"
            color="primary"
            rounded
          >
            <v-icon large>add_circle_outline</v-icon>
            Создать турнир
          </v-btn>
          <CreateDialog
            v-if="showEventCreationDialog"
            :visible="showEventCreationDialog"
            @close-event-create-dialog="showEventCreationDialog = false"
          />
        </v-col>
      </v-row>
      <v-row v-if="events.length !== 0">
        <v-col
          v-for="(eventElement, index) in events"
          :key="index"
          cols="4"
        >
          <EventCard
            :event="eventElement"
            v-on:open-dialog="openEventDialog"
          />
        </v-col>
        <RegDialog
          :visible="showRegForm"
          :event-id="focusedEventId"
          @close-reg-dialog="showRegForm = false"
        />
      </v-row>
      <LoadingStub v-else />
    </v-col>
  </v-row>
</template>

<script>
import EventFilterComponent from './filter/EventFilterComponent'
import EventCardComponent from './EventCardComponent'
import { END_POINTS } from '../../util/constants/EndPointsConstants'
import { HTTPResponseStatusConstants } from '../../util/constants/CommonConstants'
import EventRegDialogComponent from './EventRegDialogComponent'
import EventCreateComponent from './EventCreateComponent'
import LoadingStub from '../../util/components/LoadingStub'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'
import UserCookiesClass from '../../../store/cookie/UserCookiesClass'

export default {
  name: 'EventPanelComponent',
  components: {
    LoadingStub,
    EventFilter: EventFilterComponent,
    EventCard: EventCardComponent,
    RegDialog: EventRegDialogComponent,
    CreateDialog: EventCreateComponent
  },
  data () {
    return {
      cardWidth: 3,
      filter: {
        isHidden: true
      },
      events: [],
      showRegForm: false,
      showEventCreationDialog: false,
      focusedEventId: ''
    }
  },
  computed: {
    filterWidth () {
      return this.filter.isHidden ? 3 : 12
    }
  },
  created () {
    this.$http.get(END_POINTS.GET_ALL.EVENTS)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          this.events = response.data
        }
      })
      .catch(reason => {
        this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Can not get events information'})
        console.log(reason)
      })
  },
  methods: {
    openEventDialog (event) {
      this.focusedEventId = event.id
      this.showRegForm = true
    },
    openEventCreationDialog () {
      const userData = UserCookiesClass.getAutheticatedUser()
      if (userData == null) {
        this.$store.dispatch('notifications/add', {
          type: NOTIFICATION_TYPES.INFO,
          text: 'Please, login to create an event'
        })
        this.$router.push('/login')
      } else {
        this.showEventCreationDialog = true
      }
    }
  }
}
</script>
