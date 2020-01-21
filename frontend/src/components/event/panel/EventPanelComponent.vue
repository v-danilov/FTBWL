<template>
  <v-row align="stretch">
    <v-col>
      <v-row>
        <!--Filter -->
        <v-col :cols="filterWidth">
          <div transition="scroll-y-transition">
            <v-btn v-if="filter.isHidden"
                   rounded color="primary"
                   @click="filter.isHidden = !filter.isHidden">
              <v-icon large>search</v-icon>
              Открыть фильтр
            </v-btn>
            <EventFilter v-else @close-event-filter="filter.isHidden = true"/>
          </div>
        </v-col>
        <v-col cols="3">
          <v-btn color="primary"
                 rounded
                 @click="showEventCreationDialog = !showEventCreationDialog">
            <v-icon large>add_circle_outline</v-icon>
            Создать турнир
          </v-btn>
          <CreateDialog v-if="showEventCreationDialog"
                                     :visible="showEventCreationDialog"
                                     @close-event-create-dialog="showEventCreationDialog = false">
          </CreateDialog>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="4" v-for="(eventElement, index) in events" :key=index>
          <EventCard
            :event=eventElement
            @click="openEvent(eventElement.id)"
            v-on:open-dialog="openDialog">
          </EventCard>
        </v-col>
        <RegDialog
          :visible="showRegForm"
          :event-id="focusedEventId"
          @close-reg-dialog="showRegForm = false">
        </RegDialog>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import EventFilterComponent from './filter/EventFilterComponent'
import EventCardComponent from './EventCardComponent'
import {END_POINTS} from '../../util/constants/EndPointsConstants'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
import EventRegDialogComponent from './EventRegDialogComponent'
import EventCreateComponent from './EventCreateComponent'

export default {
  name: 'EventPanelComponent',
  components: {
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
  methods: {
    openDialog (event) {
      this.focusedEventId = event.id
      this.showRegForm = true
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
        console.log(reason)
      })
  }
}
</script>
