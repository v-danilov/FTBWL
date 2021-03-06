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
        v-if="!statusUpdating"
        :entity-key-word="{humanName:'турнир', systemName:'event'}"
        :event-status="selectedEvent.status.code"
        @event-status-changed="refreshEventOnStatusChanged"
      />
      <LoadingStub v-else />
      <EIBody
        @hide-info-components="changeVisibility"
        :selected-event="selectedEvent || []"
      />
    </template>
  </div>
</template>

<script>
import EventInfoBodyComponent from '@/components/event/info/side/EventInfoBodyComponent'
import EventInfoHeaderComponent from '@/components/event/info/side/EventInfoHeaderComponent'
import { END_POINTS } from '@/components/util/constants/EndPointsConstants'
import StatusManageButtonComponent from '@/components/event/StatusManageButtonComponent'
import { ACTIONS } from '@/components/util/constants/ActionConstants'
import ErrorComponent from '@/components/util/components/ErrorComponent'
import LoadingStub from '@/components/util/components/LoadingStub'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

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
      statusUpdating: false,
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
      this.statusUpdating = true
      this.$http.put(END_POINTS.EVENTS.UPDATE_STATUS.replace('{id}', this.selectedEvent.id), { code: value })
        .then(response => {
          this.selectedEvent.status = response.data.status
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.SUCCESS, text: 'Event status updated'})
        })
        .catch(error => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Can not update event status'})
          console.log('Failed to refresh event. ' + error.message)
        })
        .finally(() => { this.statusUpdating = false })
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
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Can not open event'})
          console.log(error)
          this.$router.push('/')
        })
        .finally(() => { this.loading = false })
    }
  }
}
</script>
