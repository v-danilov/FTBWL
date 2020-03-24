<template>
  <div class="mt-3" v-if="buttonParams !== null">
    <v-btn class="mr-3" v-for="(param, index) in buttonParams" :key="index"
            @click="updateStatus(param.nextStateCode)"
            :color="param.color"
            rounded outlined>
      {{param.nextStateText}}
    </v-btn>
  </div>
</template>

<script>
import {EVENT_STATUS_CODE} from '../util/constants/EventStatusNames'
import statusStyleByCode from '../util/statusStyleByCode'
import ConfirmationDialogComponent from '../dialogs/ConfirmationDialogComponent'

export default {
  name: 'StatusManageButtonComponent',
  components: {ConfirmationDialogComponent},
  props: {
    eventStatus: String
  },
  computed: {
    buttonParams () {
      // Define what type of event status management to be displayed
      // Calculate text, next state code (to perform action) and also color of the button depending on the next action
      switch (this.eventStatus) {
        case EVENT_STATUS_CODE.REG_WAIT:
          return [{nextStateText: 'Открыть регистрацию',
            nextStateCode: EVENT_STATUS_CODE.REG_ON,
            color: statusStyleByCode(EVENT_STATUS_CODE.REG_ON).color},
          {nextStateText: 'Отменить турнир',
            nextStateCode: EVENT_STATUS_CODE.CANCEL,
            color: statusStyleByCode(EVENT_STATUS_CODE.CANCEL).color}]
        case EVENT_STATUS_CODE.REG_ON:
          return [{nextStateText: 'Закрыть регистрацию',
            nextStateCode: EVENT_STATUS_CODE.REG_OFF,
            color: statusStyleByCode(EVENT_STATUS_CODE.REG_OFF).color},
          {nextStateText: 'Отменить турнир',
            nextStateCode: EVENT_STATUS_CODE.CANCEL,
            color: statusStyleByCode(EVENT_STATUS_CODE.CANCEL).color}]
        case EVENT_STATUS_CODE.REG_OFF:
          return [{nextStateText: 'Начать турнир',
            nextStateCode: EVENT_STATUS_CODE.START,
            color: statusStyleByCode(EVENT_STATUS_CODE.START).color},
          {nextStateText: 'Отменить турнир',
            nextStateCode: EVENT_STATUS_CODE.CANCEL,
            color: statusStyleByCode(EVENT_STATUS_CODE.CANCEL).color}]
        case EVENT_STATUS_CODE.START:
          return [{nextStateText: 'Начать игру',
            nextStateCode: EVENT_STATUS_CODE.PLAY,
            color: statusStyleByCode(EVENT_STATUS_CODE.PLAY).color}]
        case EVENT_STATUS_CODE.PLAY:
          return [{nextStateText: 'Приостановить турнир',
            nextStateCode: EVENT_STATUS_CODE.PAUSE,
            color: statusStyleByCode(EVENT_STATUS_CODE.PAUSE).color},
          {nextStateText: 'Закончить трунир',
            nextStateCode: EVENT_STATUS_CODE.END,
            color: statusStyleByCode(EVENT_STATUS_CODE.END).color}]
        case EVENT_STATUS_CODE.PAUSE:
          return [{nextStateText: 'Продолжить турнир',
            nextStateCode: EVENT_STATUS_CODE.PAUSE,
            color: statusStyleByCode(EVENT_STATUS_CODE.PAUSE).color}]
        default:
          return null
      }
    }
  },
  methods: {
    async updateStatus (newStatusCode) {
      if (newStatusCode === EVENT_STATUS_CODE.CANCEL) {
        // Do not emit refresh event if user declined cancellation
        if (!await this.$root.$confirmationDialog.call(this, 'Подтвердите отмену турнира', 'Вы действительно хотите отменить данный турнир?')) {
          return
        }
      }
      this.$emit('event-status-changed', newStatusCode)
    }
  }
}
</script>

<style scoped>

</style>
