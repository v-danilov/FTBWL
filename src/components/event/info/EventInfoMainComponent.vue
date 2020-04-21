<template>
  <div>
    <template v-if="loading">
      <loading-stub />
    </template>
    <template v-if="selectedEvent">
      <EIHeader
        v-if="!componentsHidden"
        :selected-event="selectedEvent"
      />
      <StatusManageButton
        :event-status="selectedEvent.status.code"
        @event-status-changed="refreshEventOnStatusChanged"
      />
      <EIBody
        @hide-info-components="changeVisibility"
        :selected-event="selectedEvent || []"
      />
    </template>
    <template v-if="loadingError">
      <error-component />
    </template>
  </div>
</template>

<script>
import EventInfoBodyComponent from './side/EventInfoBodyComponent'
import EventInfoHeaderComponent from './side/EventInfoHeaderComponent'
import { HTTPResponseStatusConstants } from '../../util/constants/CommonConstants'
import { END_POINTS } from '../../util/constants/EndPointsConstants'
import StatusManageButtonComponent from '../StatusManageButtonComponent'
import { ACTIONS } from '../../util/constants/ActionConstants'
import ErrorComponent from '../../util/components/ErrorComponent'
import LoadingStub from '../../util/components/LoadingStub'

export default {
  name: 'EventInfoComponent',
  components: {
    LoadingStub,
    ErrorComponent,
    StatusManageButton: StatusManageButtonComponent,
    EIHeader: EventInfoHeaderComponent,
    EIBody: EventInfoBodyComponent
  },
  props: ['eventId'],
  data () {
    return {
      loading: true,
      loadingError: false,
      selectedEvent: null,
      componentsHidden: true
    }
  },
  created () {
    this.checkCurrentEventId()
    this.loadEvent()
  },
  methods: {
    changeVisibility (value) {
      this.componentsHidden = value
    },
    refreshEventOnStatusChanged (value) {
      this.$http.put(END_POINTS.EVENTS.UPDATE_STATUS.replace('{id}', this.selectedEvent.id), { code: value })
        .then(response => {
          this.selectedEvent.status = response.data.status
        })
        .catch(error => {
          console.log('Failed to refresh event. ' + error.message)
        })
    },
    checkCurrentEventId () {
      if (this.$store.getters.currentActiveEventID === null) {
        this.$store.dispatch(ACTIONS.COMMONS.CURRENT_ACTIVE_EVENT_ID, this.eventId)
      }
    },
    loadEvent () {
      this.$http.get(END_POINTS.EVENTS.DEFAULT + this.eventId)
        .then(response => {
          this.selectedEvent = response.data
        })
        .catch(error => {
          if (error.response.status === HTTPResponseStatusConstants.NOT_FOUND) {
            this.loadingError = true
          }
        }).finally(() => { this.loading = false })
    }
  }
}
</script>
