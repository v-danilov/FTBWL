<template>
  <div
    v-if="buttonParams !== null"
    class="mt-3"
  >
    <v-btn
      v-for="(param, index) in buttonParams"
      :key="index"
      @click="updateStatus(param.nextStateCode)"
      :color="param.color"
      class="mr-3"
      rounded
      outlined
    >
      {{ param.nextStateText }}
    </v-btn>
  </div>
</template>

<script>
import { EVENT_STATUS_CODE } from '../util/constants/EventStatusNames'
import statusStyleByCode from '../util/statusStyleByCode'
import ConfirmationDialogComponent from '../dialogs/ConfirmationDialogComponent'

export default {
  name: 'StatusManageButtonComponent',
  components: { ConfirmationDialogComponent },
  props: {
    entityKeyWord: Object,
    eventStatus: String
  },
  computed: {
    buttonParams () {
      // Define what type of event status management to be displayed
      // Calculate text, next state code (to perform action) and also color of the button depending on the next action
      switch (this.eventStatus) {
        case EVENT_STATUS_CODE.REG_WAIT:
          return [{
            nextStateText: 'Открыть регистрацию',
            nextStateCode: EVENT_STATUS_CODE.REG_ON,
            color: statusStyleByCode(EVENT_STATUS_CODE.REG_ON).color
          },
          {
            nextStateText: `Отменить ${this.entityKeyWord.humanName}}`,
            nextStateCode: EVENT_STATUS_CODE.CANCEL,
            color: statusStyleByCode(EVENT_STATUS_CODE.CANCEL).color
          }]
        case EVENT_STATUS_CODE.REG_ON:
          return [{
            nextStateText: 'Закрыть регистрацию',
            nextStateCode: EVENT_STATUS_CODE.REG_OFF,
            color: statusStyleByCode(EVENT_STATUS_CODE.REG_OFF).color
          },
          {
            nextStateText: `Отменить ${this.entityKeyWord.humanName}`,
            nextStateCode: EVENT_STATUS_CODE.CANCEL,
            color: statusStyleByCode(EVENT_STATUS_CODE.CANCEL).color
          }]
        case EVENT_STATUS_CODE.REG_OFF:
          return [{
            nextStateText: `Начать ${this.entityKeyWord.humanName}`,
            nextStateCode: EVENT_STATUS_CODE.START,
            color: statusStyleByCode(EVENT_STATUS_CODE.START).color
          },
          {
            nextStateText: `Отменить ${this.entityKeyWord.humanName}`,
            nextStateCode: EVENT_STATUS_CODE.CANCEL,
            color: statusStyleByCode(EVENT_STATUS_CODE.CANCEL).color
          }]
        case EVENT_STATUS_CODE.START:
          return [{
            nextStateText: `Приостановить ${this.entityKeyWord.humanName}`,
            nextStateCode: EVENT_STATUS_CODE.PAUSE,
            color: statusStyleByCode(EVENT_STATUS_CODE.PAUSE).color
          },
          {
            nextStateText: `Закончить ${this.entityKeyWord.humanName}`,
            nextStateCode: EVENT_STATUS_CODE.END,
            color: statusStyleByCode(EVENT_STATUS_CODE.END).color
          }]
        case EVENT_STATUS_CODE.PAUSE:
          return [{
            nextStateText: `Продолжить ${this.entityKeyWord.humanName}`,
            nextStateCode: EVENT_STATUS_CODE.START,
            color: statusStyleByCode(EVENT_STATUS_CODE.PAUSE).color
          }]
        default:
          return null
      }
    }
  },
  methods: {
    async updateStatus (newStatusCode) {
      if (newStatusCode === EVENT_STATUS_CODE.CANCEL) {
        // Do not emit refresh event if user declined cancellation
        if (!await this.$root.$confirmationDialog.call(this, `Подтвердите отмену ${this.entityKeyWord.humanName}а`, `Вы действительно хотите отменить данный ${this.entityKeyWord.humanName}?`)) {
          return
        }
      }
      this.$emit(`${this.entityKeyWord.systemName}-status-changed`, newStatusCode)
    }
  }
}
</script>

<style scoped>

</style>
