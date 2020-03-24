<template>
  <div>
    <template v-if="!loadingError">
    <EIHeader v-if="!componentsHidden"
                                   :selected-event="selectedEvent"/>
      <StatusManageButton :event-status="selectedEvent.status.code"
                          @event-status-changed="refreshEventOnStatusChanged"></StatusManageButton>
    <EIBody @hide-info-components="changeVisibility"
                                 :selected-event="selectedEvent || []"/>
    </template>
    <template v-else>
      <v-row>
        <v-col cols="12">
          <v-card text>
            <v-card-text>
              <v-avatar size="60%">
                <img src="https://i.pinimg.com/originals/0a/ec/eb/0aecebf4c937d4e947e96c1c5f6d63c7.jpg">
              </v-avatar>
              <h3>ТУРИК 404. ОТПРАВЛЯЮСЬ НА ПОИСКИ!</h3>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </template>
  </div>
</template>

<script>
import EventInfoBodyComponent from './side/EventInfoBodyComponent'
import EventInfoHeaderComponent from './side/EventInfoHeaderComponent'
import {HTTPResponseStatusConstants} from '../../util/constants/CommonConstants'
import {END_POINTS} from '../../util/constants/EndPointsConstants'
import StatusManageButtonComponent from '../StatusManageButtonComponent'
import {ACTIONS} from '../../util/constants/ActionConstants'

export default {
  name: 'EventInfoComponent',
  props: ['eventId'],
  components: {StatusManageButton: StatusManageButtonComponent, EIHeader: EventInfoHeaderComponent, EIBody: EventInfoBodyComponent},
  data () {
    return {
      loadingError: false,
      selectedEvent: Object,
      componentsHidden: true
    }
  },
  methods: {
    changeVisibility (value) {
      this.componentsHidden = value
    },
    refreshEventOnStatusChanged (value) {
      this.$http.put(END_POINTS.EVENTS.UPDATE_STATUS.replace('{id}', this.selectedEvent.id), {'code': value})
        .then(response => {
          this.selectedEvent = response.data
        })
        .catch(error => {
          console.log('Failed to refresh event. ' + error.message)
        })
    }
  },
  created () {
    if (this.$store.getters.currentActiveEventID === null) {
      this.$store.dispatch(ACTIONS.COMMONS.CURRENT_ACTIVE_EVENT_ID, this.eventId)
    }
  },
  beforeMount () {
    this.$http.get(END_POINTS.EVENTS.DEFAULT + this.eventId)
      .then(response => {
        this.selectedEvent = response.data
      })
      .catch(error => {
        if (error.response.status === HTTPResponseStatusConstants.NOT_FOUND) {
          this.loadingError = true
        }
      })
  }
}
</script>
